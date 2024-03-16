package com.zuluco.oculuz.repository;

import com.zuluco.oculuz.models.entities.CommentBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentBranchRepository extends JpaRepository<CommentBranch, Long> {
}
