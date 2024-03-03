package com.zuluco.oculuz.model.entities.associations.complaints;

import com.zuluco.oculuz.model.entities.Comment;
import com.zuluco.oculuz.model.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "CommentComplaints")
@Data
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
}
