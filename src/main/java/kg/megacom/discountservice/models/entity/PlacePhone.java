package kg.megacom.discountservice.models.entity;

import kg.megacom.discountservice.models.entity.parents.Phone;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "place_phones")
public class PlacePhone extends Phone {


    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    private String name;



}
