package kg.megacom.discountservice.services.impl;

import kg.megacom.discountservice.dao.UserRep;
import kg.megacom.discountservice.mappers.AccountMapper;
import kg.megacom.discountservice.mappers.UserMapper;
import kg.megacom.discountservice.models.dto.AccountDto;
import kg.megacom.discountservice.models.dto.UserDto;
import kg.megacom.discountservice.models.dto.UserPhoneDto;
import kg.megacom.discountservice.models.entity.User;
import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.AccountService;
import kg.megacom.discountservice.services.UserPhoneService;
import kg.megacom.discountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRep userRep;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserPhoneService userPhoneService;


    @Override
    public Response save(UserDto userDto) {

        Response response = Response.getResponse();

        boolean uniqueLogin = accountService.isUnique(userDto.getAccount().getLogin());
        if (uniqueLogin){
            response.setStatus(444);
            response.setMessage("Пользователь с таким логином: "+userDto.getAccount().getLogin() + " уже существует");
            return response;
        }
        AccountDto accountDto = AccountMapper.INSTANCE.userDtoToAccountDto(userDto);
        accountDto = accountService.save(accountDto);
        userDto.setAccount(accountDto);

        User user = UserMapper.INSTANCE.toUser(userDto);
        user.setQr(UUID.randomUUID().toString());
        user = userRep.save(user);

        List<UserPhoneDto> userPhoneDtoList = userPhoneService.savePhones(userDto.getUserPhoneDtoList(),user);

        userDto = UserMapper.INSTANCE.toUserDto(user);
        userDto.setUserPhoneDtoList(userPhoneDtoList);

        response.setObject(userDto);

        return response;
    }
}
