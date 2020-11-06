package kg.megacom.discountservice.services;

import kg.megacom.discountservice.models.dto.UserDiscountDto;
import kg.megacom.discountservice.models.enums.DiscountStatus;
import kg.megacom.discountservice.models.responses.Response;

import java.util.List;

public interface UserDiscountService {

    Response getUserDiscount(Long userId,Long discountId);

    Response checkUserDiscount(Long userDiscountId);

    List<UserDiscountDto> getAllCreatedCoupons(DiscountStatus discountStatus);
    void saveDiscount(UserDiscountDto userDiscountDto,DiscountStatus discountStatus);
}
