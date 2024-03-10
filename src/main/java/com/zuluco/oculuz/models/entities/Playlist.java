package com.zuluco.oculuz.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "containingPlaylists")
    private Set<Video> videos = new HashSet<>();



    // Constructors:


    public Playlist(Long id, Set<Video> videos) {
        this.id = id;
        this.videos = videos;
    }

    public Playlist() {
    }


    // Getters and Setters:


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }
}
