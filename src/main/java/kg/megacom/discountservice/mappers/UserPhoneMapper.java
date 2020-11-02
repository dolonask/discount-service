package kg.megacom.discountservice.mappers;

import kg.megacom.discountservice.models.dto.UserPhoneDto;
import kg.megacom.discountservice.models.entity.UserPhone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserPhoneMapper {

    UserPhoneMapper INSTANCE = Mappers.getMapper(UserPhoneMapper.class);

    List<UserPhone> toUserPhoneList(List<UserPhoneDto> userPhoneDtoList);

    List<UserPhoneDto> toUserPhoneDtoList(List<UserPhone> userPhoneList);
}
