package kg.megacom.discountservice.services;

import kg.megacom.discountservice.models.appDto.PlaceAppDto;
import kg.megacom.discountservice.models.responses.Response;

public interface PlaceService {

    Response save(PlaceAppDto placeAppDto);

    Response updatePlace(PlaceAppDto placeAppDto);

    Response deactivatePlace(Long id);

    Response getAllByActive(boolean active);
}
