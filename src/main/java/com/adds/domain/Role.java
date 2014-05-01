package com.adds.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "roles")
public class Role implements Serializable {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final int ROLE_USER_ID = 2;
    public static final int ROLE_ADMIN_ID = 1;

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role")
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
