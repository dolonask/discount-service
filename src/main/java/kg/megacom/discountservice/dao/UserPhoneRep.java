package kg.megacom.discountservice.dao;


import kg.megacom.discountservice.models.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhoneRep extends JpaRepository<UserPhone,Long> {
}
