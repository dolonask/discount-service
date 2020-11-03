package kg.megacom.discountservice.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int discount;
    private Date startDate;
    private Date endDate;

    private double lifeTime;

    private int dayAmount;
    private int monthAmount;
    private int couponAmount;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;



}
