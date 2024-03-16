package com.zuluco.oculuz.repository;

import com.zuluco.oculuz.models.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> getVideoById(Long sourceVideoId);
}
