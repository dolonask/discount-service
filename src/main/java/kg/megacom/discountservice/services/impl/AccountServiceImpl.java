package kg.megacom.discountservice.services.impl;

import kg.megacom.discountservice.dao.AccountRep;
import kg.megacom.discountservice.mappers.AccountMapper;
import kg.megacom.discountservice.models.dto.AccountDto;
import kg.megacom.discountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRep accountRep;
    @Override
    public AccountDto save(AccountDto accountDto) {
        return AccountMapper.INSTANCE.toAccountDto(accountRep.save(AccountMapper.INSTANCE.toAccount(accountDto)));
    }

    @Override
    public boolean isUnique(String login) {
        return accountRep.existsByLogin(login);
    }
}
