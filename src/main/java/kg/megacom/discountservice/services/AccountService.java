package kg.megacom.discountservice.services;


import kg.megacom.discountservice.models.dto.AccountDto;

public interface AccountService {

    AccountDto save(AccountDto accountDto);
    boolean isUnique(String login);
}
