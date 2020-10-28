package kg.megacom.discountservice.services;

import kg.megacom.discountservice.models.dto.PlacePhoneDto;
import kg.megacom.discountservice.models.entity.Place;
import kg.megacom.discountservice.models.entity.PlacePhone;

import java.util.List;

public interface PlacePhoneService {
    List<PlacePhoneDto> save(List<PlacePhoneDto> placePhoneDtoList, Place place);

    List<PlacePhoneDto> updatePlacePhones(List<PlacePhoneDto> placePhoneDtoList, Place place);

    List<PlacePhone> findAllByPlaceIds(List<Place> placeList);
}
