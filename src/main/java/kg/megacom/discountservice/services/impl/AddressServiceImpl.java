package kg.megacom.discountservice.services.impl;

import kg.megacom.discountservice.dao.AddressRep;
import kg.megacom.discountservice.mappers.AddressMapper;
import kg.megacom.discountservice.models.dto.AddressDto;
import kg.megacom.discountservice.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRep addressRep;

    @Override
    public AddressDto save(AddressDto addressDto) {
        return AddressMapper.INSTANCE.toAddressDto(addressRep.save(AddressMapper.INSTANCE.toAddress(addressDto)));
    }
}
