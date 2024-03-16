package com.zuluco.oculuz.repository;

import com.zuluco.oculuz.models.entities.intermediates.Mark;
import com.zuluco.oculuz.models.entities.intermediates.serializables.SerializableMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, SerializableMarkId> {
}
