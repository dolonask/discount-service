package kg.megacom.discountservice.services;

import kg.megacom.discountservice.models.dto.AddressDto;

public interface AddressService {
    AddressDto save(AddressDto addressDto);
}
