package com.wit.TickHere.entity;

import jakarta.persistence.*;
import lombok.*;
import com.wit.TickHere.constant.ERole;

@Entity
@Table(name = "Role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @Column(name = "role_id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ERole name;
}
