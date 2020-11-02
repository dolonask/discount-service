package kg.megacom.discountservice.models.dto;

import kg.megacom.discountservice.models.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDto {

    private Long id;

    @NotNull
    private String name;

    private boolean active;


    private AccountDto account;


    private Role role;

    private String qr;

    private List<UserPhoneDto> userPhoneDtoList;
}
