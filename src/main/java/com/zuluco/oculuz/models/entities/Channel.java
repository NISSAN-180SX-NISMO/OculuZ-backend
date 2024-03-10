package com.zuluco.oculuz.models.entities;
import com.zuluco.oculuz.models.entities.intermediates.Subscription;
import com.zuluco.oculuz.models.entities.intermediates.complaints.ChannelComplaint;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Date registDate;
    @Column
    private String description;
    @Column
    private String paymentAccount;
    @Column
    private String email;
    @Column
    private String avatarUrl;
    @Column
    private String headerUrl;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "channel")
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "channel")
    private List<Subscription> subscribers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "ChannelTopic", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "channelId"), // Столбец в промежуточной таблице, связывающий с Channel
            inverseJoinColumns = @JoinColumn(name = "topicId") // Столбец в промежуточной таблице, связывающий с Topic
    )
    private List<Topic> topics = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "ChannelComplaintLink", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "channelId"), // Столбец в промежуточной таблице, связывающий с Channel
            inverseJoinColumns = @JoinColumn(name = "complaintId") // Столбец в промежуточной таблице, связывающий с Topic
    )
    private List<ChannelComplaint> complaints = new ArrayList<>();


    // Constructors:

    public Channel(
            Long id,
            String name,
            Date registDate,
            String description,
            String paymentAccount,
            String email,
            String avatarUrl,
            String headerUrl,
            User author,
            List<Video> videos,
            List<Subscription> subscribers,
            List<Topic> topics,
            List<ChannelComplaint> complaints
    ) {
        this.id = id;
        this.name = name;
        this.registDate = registDate;
        this.description = description;
        this.paymentAccount = paymentAccount;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.headerUrl = headerUrl;
        this.author = author;
        this.videos = videos;
        this.subscribers = subscribers;
        this.topics = topics;
        this.complaints = complaints;
    }

    public Channel() {
    }


    // Getters and setters:



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Subscription> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscription> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<ChannelComplaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<ChannelComplaint> complaints) {
        this.complaints = complaints;
    }
}
