package kg.megacom.discountservice.dao;

import kg.megacom.discountservice.models.entity.Discount;
import kg.megacom.discountservice.models.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRep extends JpaRepository<Discount,Long> {

    Discount findByActiveAndPlace(boolean active,Place place);
    Discount findByActiveIsTrueAndId(Long id);
    List<Discount> findAllByActive( boolean active);
}
