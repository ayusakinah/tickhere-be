package model.constant;

import jakarta.persistence.*;
import lombok.*;
import utils.constant.ERole;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_role")
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole name;
}
