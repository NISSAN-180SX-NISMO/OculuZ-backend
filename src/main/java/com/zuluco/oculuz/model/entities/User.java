package com.zuluco.oculuz.model.entities;

import com.zuluco.oculuz.model.entities.complaints.*;
import com.zuluco.oculuz.model.entities.associations.*;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column
    private String username;
    @Column
    private String passwordHash;
    @Column
    private String accessPermission;
    @Column
    private Boolean banned;
    @Column
    private Date banEndDate;
    @Column
    private Date birthDate;
    @Column
    private String avatarImgLink;
    @Column
    private String headerImgLink;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "user")
    private Set<Subscription> subscriptions;

    @OneToMany(mappedBy = "user")
    private Set<View> views; // Список просмотров пользователя

    @OneToMany(mappedBy = "user")
    private Set<Mark> marks; // Список оценок пользователя

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "author")
    private Set<Channel> channels;

    @OneToMany(mappedBy = "author")
    private Set<ChannelComplaint> channelComplaints;

    @OneToMany(mappedBy = "author")
    private Set<VideoComplaint> videoComplaints;

    @OneToMany(mappedBy = "author")
    private Set<CommentComplaint> commentComplaints;
}