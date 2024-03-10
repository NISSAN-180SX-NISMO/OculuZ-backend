package com.zuluco.oculuz.models.entities.intermediates;
import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.models.entities.intermediates.serializables.SerializableSubscriptionId;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "subscriptions")
@IdClass(SerializableSubscriptionId.class) // Ссылка на класс, который представляет составной ключ
public class Subscription {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user; // Ссылка на пользователя

    @Id
    @ManyToOne
    @JoinColumn(name = "channelId")
    private Channel channel; // Ссылка на видео

    @Column
    private Date startDate;


    // Constructors:


    public Subscription(User user, Channel channel, Date startDate) {
        this.user = user;
        this.channel = channel;
        this.startDate = startDate;
    }

    public Subscription() {
    }


    // Getters and Setters:


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
