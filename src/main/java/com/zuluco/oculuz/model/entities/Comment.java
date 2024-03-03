package com.zuluco.oculuz.model.entities;

import com.zuluco.oculuz.model.entities.associations.complaints.CommentComplaint;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comments")
@Data
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
}
