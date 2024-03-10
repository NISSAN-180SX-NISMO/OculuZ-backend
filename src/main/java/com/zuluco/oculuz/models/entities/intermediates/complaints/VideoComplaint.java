package com.zuluco.oculuz.models.entities.intermediates.complaints;

import com.zuluco.oculuz.models.entities.User;
import com.zuluco.oculuz.models.entities.Video;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "VideoComplaints")
public class VideoComplaint extends Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Video video;
    @ManyToOne
    private User author;

    @ManyToMany(mappedBy = "complaints")
    private Set<Video> videos = new HashSet<>();


    // Constructors:


    public VideoComplaint(Long id, Video video, User author, Set<Video> videos) {
        this.id = id;
        this.video = video;
        this.author = author;
        this.videos = videos;
    }

    public VideoComplaint() {
    }


    // Getters and Setters:


    public Long getId() { 
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }
}
