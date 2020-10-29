package kg.megacom.discountservice.services;

import kg.megacom.discountservice.models.dto.DiscountDto;
import kg.megacom.discountservice.models.responses.Response;

public interface DiscountService {
    Response save(DiscountDto discountDto);

    Response deactivateDiscount(Long id);

    Response getDiscount(Long id);

    Response getAllByActivity(boolean active);
}
