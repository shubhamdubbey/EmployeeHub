package com.cts.hr.entity;

import com.cts.hr.utility.Roles;
import jakarta.persistence.*;

@Entity
@Table(name = "login_details")
public class LoginDetails {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Roles roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
