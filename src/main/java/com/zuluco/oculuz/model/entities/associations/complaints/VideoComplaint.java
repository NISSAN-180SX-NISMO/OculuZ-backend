package com.zuluco.oculuz.model.entities.associations.complaints;

import com.zuluco.oculuz.model.entities.User;
import com.zuluco.oculuz.model.entities.Video;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "VideoComplaints")
@Data
public class VideoComplaint extends Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Video video;
    @ManyToOne
    private User author;

    @ManyToMany(mappedBy = "complaints")
    private Set<Video> videos = new HashSet<>();


}
