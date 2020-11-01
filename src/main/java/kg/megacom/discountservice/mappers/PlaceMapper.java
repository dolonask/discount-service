package kg.megacom.discountservice.mappers;

import kg.megacom.discountservice.models.appDto.PlaceAppDto;
import kg.megacom.discountservice.models.dto.AddressDto;
import kg.megacom.discountservice.models.dto.PlacePhoneDto;
import kg.megacom.discountservice.models.entity.Place;
import kg.megacom.discountservice.models.entity.PlacePhone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    @Mappings(
            {
                    @Mapping(source = "place.address.street",target = "street"),
                    @Mapping(source = "place.address.house",target = "house"),
                    @Mapping(source = "placePhoneDtoList",target = "placePhoneList")
            }
    )
    PlaceAppDto toPlaceAppDto(Place place, List<PlacePhoneDto> placePhoneDtoList);

    default List<PlaceAppDto> toAppPlaceDto(List<PlacePhone> placePhoneList){
       List<PlaceAppDto> placeAppDtos = new ArrayList<>();
        placeAppDtos = placePhoneList.stream().map(x-> {
            PlaceAppDto placeApp = new PlaceAppDto();
            placeApp.setHouse(x.getPlace().getAddress().getHouse());
            placeApp.setActive(x.getPlace().isActive());
            placeApp.setStreet(x.getPlace().getAddress().getStreet());
            placeApp.setName(x.getPlace().getName());
            placeApp.setPlacePhoneList(PlacePhoneMapper.INSTANCE.toPlacePhoneDtoList(placePhoneList));
            placeApp.setId(x.getPlace().getId());
            return placeApp;
        }).collect(Collectors.toList());
        return placeAppDtos;
   }

   default Place placeAppDtoToPlace(PlaceAppDto placeAppDto, AddressDto addressDto){
        Place place = new Place();
       place.setName(placeAppDto.getName());
       place.setActive(placeAppDto.isActive());
       place.setAddress(AddressMapper.INSTANCE.toAddress(addressDto));
       place.setQr(UUID.randomUUID().toString());
       return place;
   }
}
