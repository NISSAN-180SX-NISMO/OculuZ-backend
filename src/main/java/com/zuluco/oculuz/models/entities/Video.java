package com.zuluco.oculuz.models.entities;

import com.zuluco.oculuz.models.entities.intermediates.*;
import com.zuluco.oculuz.models.entities.intermediates.complaints.CommentComplaint;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.zuluco.oculuz.models.entities.intermediates.serializables.VideoUUIDGenerator")
    private String id;

    @Column
    private String title;
    @Column
    private String url;
    @Column
    private String description;
    @Column
    private Long duration;
    @Column
    private String previewUrl;
    @Column
    private LocalDateTime uploadDate;
    @Column
    private LocalDateTime editDate;
    @Column
    private boolean monetized;
    @Column
    private boolean adultContent;
    @Column
    private boolean banned;
    @OneToOne(mappedBy = "video")
    private CommentBranch commentBranch;

    @ManyToOne
    private Channel channel;

    @OneToMany(mappedBy = "video")
    private List<View> views; // Список просмотров для видео

    @OneToMany(mappedBy = "video")
    private List<Mark> marks; // Список оценок для видео

    @ManyToMany
    @JoinTable(
            name = "VideoPlaylist", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "videoId"), // Столбец в промежуточной таблице, связывающий с Video
            inverseJoinColumns = @JoinColumn(name = "playlistId") // Столбец в промежуточной таблице, связывающий с Playlist
    )
    private Set<Playlist> containingPlaylists = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "VideoComplaintLink", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "videoId"), // Столбец в промежуточной таблице, связывающий с Channel
            inverseJoinColumns = @JoinColumn(name = "complaintId") // Столбец в промежуточной таблице, связывающий с Topic
    )
    private Set<CommentComplaint> complaints = new HashSet<>();


    // Constructors:


    public Video(
            String id,
            String title,
            String url,
            String description,
            Long duration,
            String previewUrl,
            LocalDateTime uploadDate,
            LocalDateTime editDate,
            boolean monetized,
            boolean adultContent,
            boolean banned,
            CommentBranch commentBranch,
            Channel channel,
            List<View> views,
            List<Mark> marks,
            Set<Playlist> containingPlaylists,
            Set<CommentComplaint> complaints
    ) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.duration = duration;
        this.previewUrl = previewUrl;
        this.uploadDate = uploadDate;
        this.editDate = editDate;
        this.monetized = monetized;
        this.adultContent = adultContent;
        this.banned = banned;
        this.commentBranch = commentBranch;
        this.channel = channel;
        this.views = views;
        this.marks = marks;
        this.containingPlaylists = containingPlaylists;
        this.complaints = complaints;
    }

    public Video() {
    }

    // Getters and Setters:


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public boolean isMonetized() {
        return monetized;
    }

    public void setMonetized(boolean monetized) {
        this.monetized = monetized;
    }

    public boolean isAdultContent() {
        return adultContent;
    }

    public void setAdultContent(boolean adultContent) {
        this.adultContent = adultContent;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public CommentBranch getCommentBranch() {
        return commentBranch;
    }

    public void setCommentBranch(CommentBranch commentBranch) {
        this.commentBranch = commentBranch;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
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

    public Set<Playlist> getContainingPlaylists() {
        return containingPlaylists;
    }

    public void setContainingPlaylists(Set<Playlist> containingPlaylists) {
        this.containingPlaylists = containingPlaylists;
    }

    public Set<CommentComplaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<CommentComplaint> complaints) {
        this.complaints = complaints;
    }
}
