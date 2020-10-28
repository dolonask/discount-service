package kg.megacom.discountservice.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDiscountDto {

    private Long id;

    private Date addDate;

    private UserDto user;


    private DiscountDto discount;
}
