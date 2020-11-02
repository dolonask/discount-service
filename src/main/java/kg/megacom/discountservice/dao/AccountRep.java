package kg.megacom.discountservice.dao;

import kg.megacom.discountservice.models.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRep extends JpaRepository<Account,String> {

    boolean existsByLogin(String login);

}
