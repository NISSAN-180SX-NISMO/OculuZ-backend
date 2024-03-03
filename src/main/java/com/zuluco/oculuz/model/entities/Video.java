package com.zuluco.oculuz.model.entities;

import com.zuluco.oculuz.model.entities.associations.complaints.CommentComplaint;
import com.zuluco.oculuz.model.entities.associations.*;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "videos")
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String url;
    @Column
    private String description;
    @Column
    private Time duration;
    @Column
    private String previewUrl;
    @Column
    private Date uploadDate;
    @Column
    private Date editDate;
    @Column
    private boolean monetized;
    @Column
    private boolean adultContent;
    @Column
    private boolean banned;
    @OneToOne
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


}
