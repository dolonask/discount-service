package kg.megacom.discountservice.models.dto;

import lombok.Data;

@Data
public class RateDto {

    private Long id;
    private int rate;

    private String comment;

    private UserDto user;


    private PlaceDto place;
}
