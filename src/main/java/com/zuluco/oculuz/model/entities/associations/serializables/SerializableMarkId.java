package com.zuluco.oculuz.model.entities.associations.serializables;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SerializableMarkId implements Serializable {
    private Long user; // тип и имя должны точно соответствовать тому, что в классе Mark
    private Long video; // тип и имя должны точно соответствовать тому, что в классе Mark
}