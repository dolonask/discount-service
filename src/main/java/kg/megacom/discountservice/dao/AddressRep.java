package kg.megacom.discountservice.dao;

import kg.megacom.discountservice.models.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRep extends JpaRepository<Address,Long> {

}
