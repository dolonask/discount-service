package kg.megacom.discountservice.dao;

import kg.megacom.discountservice.models.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRep extends JpaRepository<Place,Long> {

    List<Place> findAllByActive(boolean active);
}
