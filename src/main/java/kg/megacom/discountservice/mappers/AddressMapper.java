package kg.megacom.discountservice.mappers;

import kg.megacom.discountservice.models.appDto.PlaceAppDto;
import kg.megacom.discountservice.models.dto.AddressDto;
import kg.megacom.discountservice.models.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toAddress(AddressDto addressDto);
    AddressDto toAddressDto(Address address);



     default AddressDto placeAppDtoToAddressDto(PlaceAppDto placeAppDto){
         AddressDto addressDto = new AddressDto();
         addressDto.setHouse(placeAppDto.getHouse());
         addressDto.setStreet(placeAppDto.getStreet());
         return addressDto;
     }
}
