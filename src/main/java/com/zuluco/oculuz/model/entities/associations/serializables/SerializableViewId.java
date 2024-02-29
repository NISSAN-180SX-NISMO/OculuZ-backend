package com.zuluco.oculuz.model.entities.associations.serializables;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SerializableViewId implements Serializable {
    private Long user;
    private Long video;
}