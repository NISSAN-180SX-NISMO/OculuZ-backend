package com.zuluco.oculuz.model.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;
    @Column
    private String countryName;
    @Column
    private String countryCode;
    @OneToMany(mappedBy = "country")
    private List<User> users;
}
