package kg.megacom.discountservice.models.appDto;

import kg.megacom.discountservice.models.dto.PlacePhoneDto;
import lombok.Data;

import java.util.List;

@Data
public class PlaceAppDto {

    private Long id;
    private String name;
    private String street;
    private String house;
    private List<PlacePhoneDto> placePhoneList;
    private boolean active;
}
