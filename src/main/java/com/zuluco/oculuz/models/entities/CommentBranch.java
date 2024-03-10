package com.zuluco.oculuz.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comment_branches")
public class CommentBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Video video;

    @OneToMany(mappedBy = "branch")
    private List<Comment> comments;


    // Constructors:


    public CommentBranch(
            Long id,
            Video video,
            List<Comment> comments
    ) {
        this.id = id;
        this.video = video;
        this.comments = comments;
    }

    public CommentBranch() {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
