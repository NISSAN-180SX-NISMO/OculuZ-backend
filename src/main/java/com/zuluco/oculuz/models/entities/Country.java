package com.zuluco.oculuz.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "country")
    private List<User> users;


    // Constructors:

    public Country(
            Long id,
            String name,
            List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }


    public Country() {
    }


    // Getters and Setters:


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
