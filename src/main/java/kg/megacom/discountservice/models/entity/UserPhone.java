package kg.megacom.discountservice.models.entity;

import kg.megacom.discountservice.models.entity.parents.Phone;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_phones")
public class UserPhone extends Phone {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
