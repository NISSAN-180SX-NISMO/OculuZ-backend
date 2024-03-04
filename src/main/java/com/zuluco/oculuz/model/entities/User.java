package com.zuluco.oculuz.model.entities;

import com.zuluco.oculuz.model.entities.associations.complaints.ChannelComplaint;
import com.zuluco.oculuz.model.entities.associations.complaints.CommentComplaint;
import com.zuluco.oculuz.model.entities.associations.complaints.VideoComplaint;
import com.zuluco.oculuz.model.entities.associations.complaints.*;
import com.zuluco.oculuz.model.entities.associations.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String accessPermission;
    @Column
    private Boolean banned;
    @Column
    private Date banEndDate;
    @Column
    private Date birthDate;
    @Column
    private Date registDate;
    @Column
    private String avatarUrl;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "user")
    private Set<Subscription> subscriptions;

    @OneToMany(mappedBy = "user")
    private Set<View> views; // Список просмотров пользователя

    @OneToMany(mappedBy = "user")
    private Set<Mark> marks; // Список оценок пользователя

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "author")
    private Set<Channel> channels;

    @OneToMany(mappedBy = "author")
    private Set<ChannelComplaint> channelComplaints;

    @OneToMany(mappedBy = "author")
    private Set<VideoComplaint> videoComplaints;

    @OneToMany(mappedBy = "author")
    private Set<CommentComplaint> commentComplaints;

    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }
}