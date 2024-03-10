package com.zuluco.oculuz.models.entities.intermediates;

import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.models.entities.Video;
import com.zuluco.oculuz.models.entities.intermediates.serializables.SerializableViewId;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "views")
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


    // Constructors:


    public View(User user, Video video, Date date, Time exitPoint) {
        this.user = user;
        this.video = video;
        this.date = date;
        this.exitPoint = exitPoint;
    }

    public View() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getExitPoint() {
        return exitPoint;
    }

    public void setExitPoint(Time exitPoint) {
        this.exitPoint = exitPoint;
    }
}
