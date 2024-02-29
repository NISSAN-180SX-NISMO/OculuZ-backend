package com.zuluco.oculuz.model.entities.associations;

import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.entities.Video;
import com.zuluco.oculuz.model.entities.associations.serializables.SerializableViewId;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "views")
@Data
@IdClass(SerializableViewId.class) // Ссылка на класс, который представляет составной ключ
public class View {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user; // Ссылка на пользователя

    @Id
    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video video; // Ссылка на видео

    @Column
    private Date date;
    @Column
    private Time exitPoint;

}
