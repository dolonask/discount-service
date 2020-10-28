package kg.megacom.discountservice.mappers;

import kg.megacom.discountservice.models.dto.AddressDto;
import kg.megacom.discountservice.models.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toAddress(AddressDto addressDto);
    AddressDto toAddressDto(Address address);
}
