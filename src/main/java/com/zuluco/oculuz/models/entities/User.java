package com.zuluco.oculuz.models.entities;

import com.zuluco.oculuz.models.entities.intermediates.Mark;
import com.zuluco.oculuz.models.entities.intermediates.Subscription;
import com.zuluco.oculuz.models.entities.intermediates.View;
import com.zuluco.oculuz.models.entities.intermediates.complaints.ChannelComplaint;
import com.zuluco.oculuz.models.entities.intermediates.complaints.CommentComplaint;
import com.zuluco.oculuz.models.entities.intermediates.complaints.VideoComplaint;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Date;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Column
    private Boolean banned;

    @Column
    private Date banEndDate;

    @Column
    private Date birthDate;

    @Column
    private Date registDate;

    @Column
    private String avatarUrl;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "user")
    private List<View> views;

    @OneToMany(mappedBy = "user")
    private List<Mark> marks;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "author")
    private List<Channel> channels;

    @OneToMany(mappedBy = "author")
    private List<ChannelComplaint> channelComplaints;

    @OneToMany(mappedBy = "author")
    private List<VideoComplaint> videoComplaints;

    @OneToMany(mappedBy = "author")
    private List<CommentComplaint> commentComplaints;


    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Date getBanEndDate() {
        return banEndDate;
    }

    public void setBanEndDate(Date banEndDate) {
        this.banEndDate = banEndDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<ChannelComplaint> getChannelComplaints() {
        return channelComplaints;
    }

    public void setChannelComplaints(List<ChannelComplaint> channelComplaints) {
        this.channelComplaints = channelComplaints;
    }

    public List<VideoComplaint> getVideoComplaints() {
        return videoComplaints;
    }

    public void setVideoComplaints(List<VideoComplaint> videoComplaints) {
        this.videoComplaints = videoComplaints;
    }

    public List<CommentComplaint> getCommentComplaints() {
        return commentComplaints;
    }

    public void setCommentComplaints(List<CommentComplaint> commentComplaints) {
        this.commentComplaints = commentComplaints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (!this.roles.contains(role))
            roles.add(role);
    }
}
