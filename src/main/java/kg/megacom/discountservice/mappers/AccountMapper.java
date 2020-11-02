package kg.megacom.discountservice.mappers;

import kg.megacom.discountservice.models.dto.AccountDto;
import kg.megacom.discountservice.models.dto.UserDto;
import kg.megacom.discountservice.models.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "userDto.account.login",target = "login")
    @Mapping(source = "userDto.account.password",target = "password")
    AccountDto userDtoToAccountDto(UserDto userDto);

    AccountDto toAccountDto(Account account);
    Account toAccount(AccountDto accountDto);
}
