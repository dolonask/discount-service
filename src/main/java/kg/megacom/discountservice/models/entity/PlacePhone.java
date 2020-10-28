package kg.megacom.discountservice.models.entity;

import kg.megacom.discountservice.models.parents.Phone;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "place_phones")
public class PlacePhone extends Phone {


    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;



}
