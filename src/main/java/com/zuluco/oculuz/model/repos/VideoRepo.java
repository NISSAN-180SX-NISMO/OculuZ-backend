package com.zuluco.oculuz.model.repos;

import com.zuluco.oculuz.model.entities.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends CrudRepository<Video, Long> {
}
