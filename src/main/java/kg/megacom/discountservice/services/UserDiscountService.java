package kg.megacom.discountservice.services;

import kg.megacom.discountservice.models.responses.Response;

public interface UserDiscountService {

    Response getUserDiscount(Long userId,Long discountId);
}
