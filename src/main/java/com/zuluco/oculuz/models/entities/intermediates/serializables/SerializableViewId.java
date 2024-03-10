package com.zuluco.oculuz.models.entities.intermediates.serializables;



import java.io.Serializable;
import java.util.Objects;


public class SerializableViewId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long user;
    private Long video;

    public SerializableViewId(Long user, Long video) {
        this.user = user;
        this.video = video;
    }

    public SerializableViewId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableViewId that = (SerializableViewId) o;
        return Objects.equals(user, that.user) && Objects.equals(video, that.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, video);
    }
}