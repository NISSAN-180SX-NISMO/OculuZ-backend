package com.zuluco.oculuz.model.entities.associations;

import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.entities.Video;
import com.zuluco.oculuz.model.entities.associations.serializables.SerializableMarkId;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "marks")
@Data
@IdClass(SerializableMarkId.class) // Ссылка на класс, который представляет составной ключ
public class Mark {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user; // Ссылка на пользователя

    @Id
    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video video; // Ссылка на видео

    @Column
    private markValue value;
    @Column
    private Date date;
}
