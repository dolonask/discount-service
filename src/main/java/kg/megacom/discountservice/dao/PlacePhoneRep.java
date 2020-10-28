package kg.megacom.discountservice.dao;

import kg.megacom.discountservice.models.entity.PlacePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacePhoneRep extends JpaRepository<PlacePhone,Long> {

    List<PlacePhone> findAllByPlace_Id(Long id);
}
