package com.zuluco.oculuz.repository;

import com.zuluco.oculuz.models.entities.intermediates.View;
import com.zuluco.oculuz.models.entities.intermediates.serializables.SerializableViewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewRepository extends JpaRepository<View, SerializableViewId> {
}
