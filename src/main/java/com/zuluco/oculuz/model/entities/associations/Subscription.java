package com.zuluco.oculuz.model.entities.associations;
import com.zuluco.oculuz.model.entities.Channel;
import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.entities.associations.serializables.SerializableSubscriptionId;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "subscriptions")
@Data
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
}
