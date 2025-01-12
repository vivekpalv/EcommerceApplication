package com.ecom.amazon.Entity;

import com.ecom.amazon.Enum.ROLE;
import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ROLE role = ROLE.ROLE_ADMIN;


    //Getters
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ROLE getRole() {
        return role;
    }

    //Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
