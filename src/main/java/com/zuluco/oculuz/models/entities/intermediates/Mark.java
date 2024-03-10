package com.zuluco.oculuz.models.entities.intermediates;

import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.models.entities.Video;
import com.zuluco.oculuz.models.entities.intermediates.serializables.SerializableMarkId;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "marks")
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
    private MarkType value;
    @Column
    private Date date;


    // Constructors:


    public Mark(User user, Video video, MarkType value, Date date) {
        this.user = user;
        this.video = video;
        this.value = value;
        this.date = date;
    }

    public Mark() {
    }


    // Getters and Setters:


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public MarkType getValue() {
        return value;
    }

    public void setValue(MarkType value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
