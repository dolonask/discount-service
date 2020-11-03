package kg.megacom.discountservice.mappers;


import kg.megacom.discountservice.models.dto.DiscountDto;
import kg.megacom.discountservice.models.dto.UserDiscountDto;
import kg.megacom.discountservice.models.dto.UserDto;
import kg.megacom.discountservice.models.entity.UserDiscount;
import kg.megacom.discountservice.models.enums.DiscountStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper
public interface UserDiscountMapper {
    UserDiscountMapper INSTANCE = Mappers.getMapper(UserDiscountMapper.class);

    UserDiscount toUserDiscount(UserDiscountDto userDiscountDto);
    UserDiscount toUserDiscountDto(UserDiscount userDiscount);

    default UserDiscount userDtoAndDiscountDtoToUserDiscount(UserDto userDto, DiscountDto discountDto){
        UserDiscount userDiscount = new UserDiscount();
        userDiscount.setAddDate(new Date());
        userDiscount.setUser(UserMapper.INSTANCE.toUser(userDto));
        userDiscount.setDiscount(DiscountMapper.INSTANCE.toDiscount(discountDto));
        userDiscount.setDiscountStatus(DiscountStatus.CREATED);
        return userDiscount;
    }
}
