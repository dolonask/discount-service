package kg.megacom.discountservice.models.entity;

import kg.megacom.discountservice.models.enums.DiscountStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_discounts")
public class UserDiscount {

    @Id
    @GeneratedValue
    private Long id;

    private Date addDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private DiscountStatus discountStatus;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
