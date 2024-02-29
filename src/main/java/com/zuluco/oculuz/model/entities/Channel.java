package com.zuluco.oculuz.model.entities;
import com.zuluco.oculuz.model.entities.complaints.ChannelComplaint;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "channels")
@Data
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelId;
    @Column
    private String name;
    @Column
    private Date registrationDate;
    @Column
    private String description;
    @Column
    private String paymentAccount;
    @Column
    private String email;
    @Column
    private String avatarImgLink;
    @Column
    private String headerImgLink;

    @ManyToOne
    private User author;

    @ManyToMany
    @JoinTable(
            name = "ChannelTopic", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "channelId"), // Столбец в промежуточной таблице, связывающий с Channel
            inverseJoinColumns = @JoinColumn(name = "topicId") // Столбец в промежуточной таблице, связывающий с Topic
    )
    private Set<Topic> topics = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "ChannelComplaintLink", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "channelId"), // Столбец в промежуточной таблице, связывающий с Channel
            inverseJoinColumns = @JoinColumn(name = "complaintId") // Столбец в промежуточной таблице, связывающий с Topic
    )
    private Set<ChannelComplaint> complaints = new HashSet<>();
}
