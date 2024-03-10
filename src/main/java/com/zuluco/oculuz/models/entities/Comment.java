package com.zuluco.oculuz.models.entities;

import com.zuluco.oculuz.models.entities.intermediates.complaints.CommentComplaint;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @Column
    private Date postingDate;
    @Column
    private Date editDate;

    @ManyToOne
    private User author;
    @ManyToOne
    private CommentBranch branch;

    @ManyToMany
    @JoinTable(
            name = "CommentComplaintLink", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "commentId"), // Столбец в промежуточной таблице, связывающий с Channel
            inverseJoinColumns = @JoinColumn(name = "complaintId") // Столбец в промежуточной таблице, связывающий с Topic
    )
    private Set<CommentComplaint> complaints = new HashSet<>();


    // Constructors:

    public Comment(Long id,
                   String content,
                   Date postingDate,
                   Date editDate,
                   User author,
                   CommentBranch branch,
                   Set<CommentComplaint> complaints
    ) {
        this.id = id;
        this.content = content;
        this.postingDate = postingDate;
        this.editDate = editDate;
        this.author = author;
        this.branch = branch;
        this.complaints = complaints;
    }

    public Comment() {
    }


    // Getters and setters:


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public CommentBranch getBranch() {
        return branch;
    }

    public void setBranch(CommentBranch branch) {
        this.branch = branch;
    }

    public Set<CommentComplaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<CommentComplaint> complaints) {
        this.complaints = complaints;
    }
}
