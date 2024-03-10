package com.zuluco.oculuz.repository;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.zuluco.oculuz.models.entities.RefreshToken;
import com.zuluco.oculuz.models.entities.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  @Modifying
  int deleteByUser(User user);

    void deleteAllByExpiryDateLessThan(Instant now);

    void deleteByToken(String requestRefreshToken);
}
