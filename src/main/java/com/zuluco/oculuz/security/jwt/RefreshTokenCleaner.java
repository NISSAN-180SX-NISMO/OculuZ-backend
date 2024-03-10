package com.zuluco.oculuz.security.jwt;

import com.zuluco.oculuz.security.services.RefreshTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenCleaner {

    private static final Logger logger = LoggerFactory.getLogger(RefreshTokenCleaner.class);

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Scheduled(fixedRateString = "${bezkoder.app.refreshTokenCleanerTimeMs}")
    public void cleanupExpiredTokens() {
        logger.info("Deleting expired tokens...");
        refreshTokenService.deleteExpiredTokens();
    }
}