package kg.megacom.discountservice.services;

import kg.megacom.discountservice.models.dto.UserDto;
import kg.megacom.discountservice.models.responses.Response;

public interface UserService {
    Response save(UserDto userDto);
}
