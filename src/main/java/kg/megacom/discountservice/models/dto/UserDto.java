package kg.megacom.discountservice.models.dto;

import kg.megacom.discountservice.models.enums.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;

    private boolean active;


    private AccountDto account;


    private Role role;

    private String qr;
}
