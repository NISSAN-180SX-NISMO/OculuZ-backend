package com.zuluco.oculuz.models.entities.intermediates.serializables;


import java.io.Serializable;
import java.util.Objects;


public class SerializableMarkId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long user; // тип и имя должны точно соответствовать тому, что в классе Mark
    private Long video; // тип и имя должны точно соответствовать тому, что в классе Mark


    public SerializableMarkId(Long user, Long video) {
        this.user = user;
        this.video = video;
    }

    public SerializableMarkId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableMarkId that = (SerializableMarkId) o;
        return Objects.equals(user, that.user) && Objects.equals(video, that.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, video);
    }
}