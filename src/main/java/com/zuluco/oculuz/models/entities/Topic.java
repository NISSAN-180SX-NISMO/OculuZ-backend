package com.zuluco.oculuz.models.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")

public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "topics")
    private Set<Channel> channels = new HashSet<>();


    // Constructors:


    public Topic(Long id, String name, Set<Channel> channels) {
        this.id = id;
        this.name = name;
        this.channels = channels;
    }

    public Topic() {
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

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }
}
