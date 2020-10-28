package kg.megacom.discountservice.mappers;

import kg.megacom.discountservice.models.dto.PlacePhoneDto;
import kg.megacom.discountservice.models.entity.Place;
import kg.megacom.discountservice.models.entity.PlacePhone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlacePhoneMapper {

    PlacePhoneMapper INSTANCE = Mappers.getMapper(PlacePhoneMapper.class);

    List<PlacePhone> toPlacePhoneList(List<PlacePhoneDto> placePhoneDtoList);

    List<PlacePhoneDto> toPlacePhoneDtoList(List<PlacePhone> placePhoneList);
}
