package kg.megacom.discountservice.models.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserPhoneDto  {

    private Long id;

    @Pattern(regexp = "[0-9]")
    private String phone;
}
