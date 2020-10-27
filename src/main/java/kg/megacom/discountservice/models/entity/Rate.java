package kg.megacom.discountservice.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rates")
public class Rate {

    @Id
    @GeneratedValue
    private Long id;
    private int rate;
    @Column(columnDefinition = "text")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;



}
