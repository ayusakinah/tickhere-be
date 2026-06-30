package model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "mst_profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_profile")
    private String id;
    private String identity_number;
    private String full_name;
    private Date birth_date;
    private String address;
    private String phone_number;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
