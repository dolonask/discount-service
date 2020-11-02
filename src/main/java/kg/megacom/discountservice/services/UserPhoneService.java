package kg.megacom.discountservice.services;

import kg.megacom.discountservice.models.dto.UserPhoneDto;
import kg.megacom.discountservice.models.entity.User;

import java.util.List;

public interface UserPhoneService {

    List<UserPhoneDto> savePhones(List<UserPhoneDto> userPhoneDtoList, User user);
}
