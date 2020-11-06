package kg.megacom.discountservice.services.impl;

import kg.megacom.discountservice.dao.UserDiscountRep;
import kg.megacom.discountservice.mappers.DiscountMapper;
import kg.megacom.discountservice.mappers.UserDiscountMapper;
import kg.megacom.discountservice.mappers.UserMapper;
import kg.megacom.discountservice.models.dto.DiscountDto;
import kg.megacom.discountservice.models.dto.UserDiscountDto;
import kg.megacom.discountservice.models.dto.UserDto;
import kg.megacom.discountservice.models.entity.UserDiscount;
import kg.megacom.discountservice.models.enums.DiscountStatus;
import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.DiscountService;
import kg.megacom.discountservice.services.UserDiscountService;
import kg.megacom.discountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserDiscountServiceImpl implements UserDiscountService {


    @Autowired
    private UserDiscountRep userDiscountRep;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private UserService userService;


    @Override
    public Response getUserDiscount(Long userId, Long discountId) {

        Response response = Response.getResponse();

        UserDto userDto = userService.getUserById(userId);

        if (userDto==null){
            response.setStatus(404);
            response.setMessage("Пользователь не найден");
            return response;
        }

        if (!userDto.isActive()){
            response.setStatus(000);
            response.setMessage("Пользователь не активен, обратитесь в службу поддержки");
            return response;
        }

        DiscountDto discountDto = discountService.getDiscountById(discountId);

        if (discountDto==null){
            response.setStatus(404);
            response.setMessage("Скидка не существует");
            return response;
        }
        if (!discountDto.isActive()){
            response.setStatus(000);
            response.setMessage("Скидка не активна");
            return response;
        }

        UserDiscount userDiscount = userDiscountRep.findByUserAndDiscountAndDiscountStatus(
                UserMapper.INSTANCE.toUser(userDto),
                DiscountMapper.INSTANCE.toDiscount(discountDto),
                DiscountStatus.CREATED);

        if (userDiscount!=null){
            response.setObject(UserDiscountMapper.INSTANCE.toUserDiscountDto(userDiscount));
            return response;
        }

        List<DiscountStatus> discountStatuses = new ArrayList<>();

        discountStatuses.add(DiscountStatus.CREATED);
        discountStatuses.add(DiscountStatus.USED);

        if (discountDto.getCouponAmount()!=0) {
            int sumOfUsedAndCreatedUserDiscounts = userDiscountRep.countAllByDiscountIdAndDiscountStatusIn(discountId, discountStatuses);
            if (discountDto.getCouponAmount() - sumOfUsedAndCreatedUserDiscounts <= 0) {
                response.setStatus(440);
                response.setMessage("Приносим извинения не осталось купонов");
                return response;
            }
        }
        if (discountDto.getMonthAmount()!=0){
            Date dateForMonth = new Date();
            Calendar dateForMonthly = Calendar.getInstance();
            dateForMonthly.setTime(dateForMonth);
            dateForMonthly.set(Calendar.DAY_OF_MONTH,1);
            dateForMonthly.set(Calendar.HOUR_OF_DAY,0);
            dateForMonthly.set(Calendar.MINUTE,0);
            dateForMonthly.set(Calendar.SECOND,0);

            int sumOfMonthlyUsedUserDiscount = userDiscountRep.countAllByDiscountIdAndUserIdAndDiscountStatusAndAddDateAfter(
                    discountId,
                    userId,
                    DiscountStatus.USED,
                    dateForMonthly.getTime());

            if (discountDto.getMonthAmount()<sumOfMonthlyUsedUserDiscount){
                response.setStatus(441);
                response.setMessage("Вы использовали весь ежемесячный лимит купонов");
                return response;
            }
        }

            if (discountDto.getDayAmount()!=0){

                Date today = new Date();

                Calendar dateForDaily = Calendar.getInstance();

                dateForDaily.setTime(today);
                dateForDaily.set(Calendar.HOUR_OF_DAY,0);
                dateForDaily.set(Calendar.MINUTE,0);
                dateForDaily.set(Calendar.SECOND,0);

                int sumOfDailyUsedUserDiscounts = userDiscountRep.countAllByDiscountIdAndUserIdAndDiscountStatusAndAddDateAfter(
                        discountId,
                        userId,
                        DiscountStatus.USED,
                        dateForDaily.getTime()
                        );
                if (discountDto.getDayAmount()<sumOfDailyUsedUserDiscounts){
                    response.setStatus(442);
                    response.setMessage("Вы использовали ежедневный лимит купонов");
                    return response;
                }
            }

            userDiscount = UserDiscountMapper.INSTANCE.userDtoAndDiscountDtoToUserDiscount(userDto,discountDto);

            userDiscount = userDiscountRep.save(userDiscount);

            response.setObject(UserDiscountMapper.INSTANCE.toUserDiscountDto(userDiscount));

        return response;
    }

    @Override
    public Response checkUserDiscount(Long userDiscountId) {

        Response response = Response.getResponse();


        UserDiscount userDiscount = userDiscountRep.findByIdIs(userDiscountId);

        if (userDiscount==null){
            response.setStatus(450);
            response.setMessage("У пользователя нет купона, сгенерируйте купон");
            return response;
        }


        UserDto userDto = userService.getUserById(userDiscount.getUser().getId());

        if (userDto==null){
            response.setStatus(404);
            response.setMessage("Пользователь не найден");
            return response;
        }

        if (!userDto.isActive()){
            response.setStatus(000);
            response.setMessage("Пользователь не активен, обратитесь в службу поддержки");
            return response;
        }


        if (userDiscount.getDiscountStatus().equals(DiscountStatus.CREATED)){
            response.setObject(UserDiscountMapper.INSTANCE.toUserDiscountDto(userDiscount));
            return response;
        }

        response.setStatus(451);
        response.setMessage("Неккоректный статус");
        return response;





    }

    @Override
    public List<UserDiscountDto> getAllCreatedCoupons(DiscountStatus discountStatus) {
        List<UserDiscount> userDiscountList = userDiscountRep.findAllByDiscountStatus(discountStatus);
        return UserDiscountMapper.INSTANCE.toUserDiscountDtoList(userDiscountList);
    }

    @Override
    public void saveDiscount(UserDiscountDto userDiscountDto, DiscountStatus discountStatus) {
        userDiscountDto.setDiscountStatus(discountStatus);
        userDiscountRep.save(UserDiscountMapper.INSTANCE.toUserDiscount(userDiscountDto));
    }
}
