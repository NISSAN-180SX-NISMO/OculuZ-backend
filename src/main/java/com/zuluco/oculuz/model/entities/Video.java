package com.zuluco.oculuz.model.entities;

import com.zuluco.oculuz.model.entities.complaints.CommentComplaint;
import com.zuluco.oculuz.model.entities.associations.*;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "videos")
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private boolean monetized;
    @Column
    private Date releaseDate;
    @Column
    private Date editDate;
    @Column
    private boolean adultOnly;
    @Column
    private boolean blocked;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "video")
    private List<View> views; // Список просмотров для видео

    @OneToMany(mappedBy = "video")
    private List<Subscription> subscriptions;

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


}
