package kg.megacom.discountservice.models.dto;


import lombok.Data;

@Data
public class PlaceDto {

    private Long id;

    private String name;


    private AddressDto address;

    private String qr;

    private boolean active;
}
