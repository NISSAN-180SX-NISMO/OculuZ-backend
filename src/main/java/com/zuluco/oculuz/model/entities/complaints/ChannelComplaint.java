package com.zuluco.oculuz.model.entities.complaints;

import com.zuluco.oculuz.model.entities.Channel;
import com.zuluco.oculuz.model.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ChannelComplaints")
@Data
public class ChannelComplaint extends Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    @ManyToOne
    private Channel channel;
    @ManyToOne
    private User author;

    @ManyToMany(mappedBy = "complaints")
    private Set<Channel> channels = new HashSet<>();

}
