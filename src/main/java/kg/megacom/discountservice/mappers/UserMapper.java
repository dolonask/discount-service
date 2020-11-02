package kg.megacom.discountservice.mappers;

import kg.megacom.discountservice.models.dto.UserDto;
import kg.megacom.discountservice.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
