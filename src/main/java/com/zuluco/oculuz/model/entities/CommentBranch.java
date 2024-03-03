package com.zuluco.oculuz.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comment_branches")
@Data
public class CommentBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Video video;

    @OneToMany(mappedBy = "branch")
    private List<Comment> comments;
}
