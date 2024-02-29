package com.zuluco.oculuz.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @Column
    private String name;

    @ManyToMany(mappedBy = "topics")
    private Set<Channel> channels = new HashSet<>();
}
