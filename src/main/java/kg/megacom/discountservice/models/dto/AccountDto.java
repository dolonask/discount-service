package kg.megacom.discountservice.models.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AccountDto {

    @NotNull
    private String login;

    @Min(value = 4,message = "Пароль должен быть больше 4-х символов")
    private String password;
}
