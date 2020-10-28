package kg.megacom.discountservice.models.entity;

import kg.megacom.discountservice.models.enums.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private boolean active;

    @OneToOne
    @JoinColumn(name = "account")
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String qr;


}
