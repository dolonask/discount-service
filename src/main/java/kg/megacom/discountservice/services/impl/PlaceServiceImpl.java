package kg.megacom.discountservice.services.impl;

import kg.megacom.discountservice.dao.PlaceRep;
import kg.megacom.discountservice.mappers.AddressMapper;
import kg.megacom.discountservice.mappers.PlaceMapper;
import kg.megacom.discountservice.models.appDto.PlaceAppDto;
import kg.megacom.discountservice.models.dto.AddressDto;
import kg.megacom.discountservice.models.dto.PlacePhoneDto;
import kg.megacom.discountservice.models.entity.Place;
import kg.megacom.discountservice.models.entity.PlacePhone;
import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.AddressService;
import kg.megacom.discountservice.services.PlacePhoneService;
import kg.megacom.discountservice.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private AddressService addressService;

    @Autowired
    private PlaceRep placeRep;

    @Autowired
    private PlacePhoneService placePhoneService;
    @Override
    public Response save(PlaceAppDto placeAppDto) {

        Response response = Response.getResponse();
            AddressDto addressDto = new AddressDto();
            addressDto.setHouse(placeAppDto.getHouse());
            addressDto.setStreet(placeAppDto.getStreet());
            addressDto = addressService.save(addressDto);
            Place place = new Place();
            place.setName(placeAppDto.getName());
            place.setActive(placeAppDto.isActive());
            place.setAddress(AddressMapper.INSTANCE.toAddress(addressDto));
            place.setQr(UUID.randomUUID().toString());
            place = placeRep.save(place);
            List<PlacePhoneDto> placePhoneDtoList = placePhoneService.save(placeAppDto.getPlacePhoneList(), place);
            placeAppDto = PlaceMapper.INSTANCE.toPlaceAppDto(place, placePhoneDtoList);
            response.setObject(placeAppDto);


        return response;
    }

    @Override
    public Response updatePlace(PlaceAppDto placeAppDto) {
        Response response = Response.getResponse();
        Place place = placeRep.findById(placeAppDto.getId()).orElse(null);
        if (place==null){
            response.setStatus(404);
            response.setMessage("Заведение не найдено");
            return response;
        }
        AddressDto addressDto = AddressMapper.INSTANCE.toAddressDto(place.getAddress());
        addressDto.setStreet(placeAppDto.getStreet());
        addressDto.setHouse(placeAppDto.getHouse());
        addressDto = addressService.save(addressDto);
        List<PlacePhoneDto> placePhoneDtoList = placePhoneService.updatePlacePhones(placeAppDto.getPlacePhoneList(),place);
        place.setName(placeAppDto.getName());
        placeAppDto.setActive(placeAppDto.isActive());
        place.setAddress(AddressMapper.INSTANCE.toAddress(addressDto));
        place = placeRep.save(place);
        placeAppDto = PlaceMapper.INSTANCE.toPlaceAppDto(place, placePhoneDtoList);
        response.setObject(placeAppDto);
        return response;
    }

    @Override
    public Response deactivatePlace(Long id) {
        Response response = Response.getResponse();
        Place place = placeRep.findById(id).orElse(null);
        if (place==null){
            response.setStatus(404);
            response.setMessage("Заведение не найдено");
            return response;
        }
        place.setActive(false);
        place = placeRep.save(place);
        response.setMessage("Успешно удалено");
        response.setObject(place);
        return response;
    }

    @Override
    public Response getAllByActive(boolean active) {
        Response response = Response.getResponse();
        List<Place> placeList = placeRep.findAllByActive(active);
        List<PlacePhone> placePhoneList = placePhoneService.findAllByPlaceIds(placeList);
        response.setObject(PlaceMapper.INSTANCE.toAppPlaceDto(placePhoneList));
        return response;
    }
}
