package com.zuluco.oculuz.models.entities.intermediates.complaints;

import com.zuluco.oculuz.models.entities.Channel;
import com.zuluco.oculuz.models.entities.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ChannelComplaints")
public class ChannelComplaint extends Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Channel channel;
    @ManyToOne
    private User author;

    @ManyToMany(mappedBy = "complaints")
    private Set<Channel> channels = new HashSet<>();


    // Constructors:


    public ChannelComplaint(Long id, Channel channel, User author, Set<Channel> channels) {
        this.id = id;
        this.channel = channel;
        this.author = author;
        this.channels = channels;
    }

    public ChannelComplaint() {
    }

    // Getters and Setters:


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }
}
