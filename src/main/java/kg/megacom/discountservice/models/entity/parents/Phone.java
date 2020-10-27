package kg.megacom.discountservice.models.entity.parents;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phone;

}
