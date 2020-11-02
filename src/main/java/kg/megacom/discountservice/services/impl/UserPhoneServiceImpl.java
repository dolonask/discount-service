package kg.megacom.discountservice.services.impl;

import kg.megacom.discountservice.dao.UserPhoneRep;
import kg.megacom.discountservice.mappers.UserPhoneMapper;
import kg.megacom.discountservice.models.dto.UserPhoneDto;
import kg.megacom.discountservice.models.entity.User;
import kg.megacom.discountservice.models.entity.UserPhone;
import kg.megacom.discountservice.services.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserPhoneServiceImpl implements UserPhoneService {

    @Autowired
    private UserPhoneRep userPhoneRep;

    @Override
    public List<UserPhoneDto> savePhones(List<UserPhoneDto> userPhoneDtoList, User user) {
        List<UserPhone> userPhoneList = UserPhoneMapper.INSTANCE.toUserPhoneList(userPhoneDtoList);
         userPhoneList = userPhoneList.stream()
                .peek(x->x.setUser(user)).collect(Collectors.toList());
        userPhoneList = userPhoneRep.saveAll(userPhoneList);

        return UserPhoneMapper.INSTANCE.toUserPhoneDtoList(userPhoneList);
    }
}
