package com.jvtpe.domain;

import com.jvtpe.domain.enums.UserRole;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_role")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private UserRole name;


    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }

}

