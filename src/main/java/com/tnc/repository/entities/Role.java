package com.tnc.repository.entities;

import com.tnc.service.domain.RoleEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
