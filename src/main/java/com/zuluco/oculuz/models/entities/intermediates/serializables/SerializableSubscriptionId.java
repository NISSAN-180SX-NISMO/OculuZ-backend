package com.zuluco.oculuz.models.entities.intermediates.serializables;


import java.io.Serializable;
import java.util.Objects;


public class SerializableSubscriptionId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long user;
    private Long channel;

    public SerializableSubscriptionId(Long user, Long channel) {
        this.user = user;
        this.channel = channel;
    }

    public SerializableSubscriptionId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableSubscriptionId that = (SerializableSubscriptionId) o;
        return Objects.equals(user, that.user) && Objects.equals(channel, that.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, channel);
    }
}
