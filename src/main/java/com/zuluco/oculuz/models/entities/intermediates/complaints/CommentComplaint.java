package com.zuluco.oculuz.models.entities.intermediates.complaints;

import com.zuluco.oculuz.models.entities.Comment;
import com.zuluco.oculuz.models.entities.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "CommentComplaints")
public class CommentComplaint extends Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Comment comment;
    @ManyToOne
    private User author;

    @ManyToMany(mappedBy = "complaints")
    private Set<Comment> comments = new HashSet<>();

    // Constructors


    public CommentComplaint(Long id, Comment comment, User author, Set<Comment> comments) {
        this.id = id;
        this.comment = comment;
        this.author = author;
        this.comments = comments;
    }

    public CommentComplaint() {
    }

    // Getters and Setters:


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
