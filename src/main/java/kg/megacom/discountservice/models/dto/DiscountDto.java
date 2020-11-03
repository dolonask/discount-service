package kg.megacom.discountservice.models.dto;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
public class DiscountDto {

    private Long id;


    @Min(value = 5,message = "Скидка должна быть больше или равна 5")
    @Max(value = 100,message = "Скидка должна быть меньше или равно 100")
    private int discount;
    @FutureOrPresent
    private Date startDate;
    @Future
    private Date endDate;

    private double lifeTime;

    private int dayAmount;
    private int monthAmount;
    private int  couponAmount;

    private boolean active;

    private PlaceDto place;
}
