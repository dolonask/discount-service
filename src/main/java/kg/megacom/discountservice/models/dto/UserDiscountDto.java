package kg.megacom.discountservice.models.dto;

import kg.megacom.discountservice.models.enums.DiscountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class UserDiscountDto {

    private Long id;

    private Date addDate;

    private UserDto user;

    private DiscountStatus discountStatus;

    private DiscountDto discount;
}
