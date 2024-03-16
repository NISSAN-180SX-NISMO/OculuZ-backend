package com.zuluco.oculuz.models.entities.intermediates.serializables;

import com.zuluco.oculuz.models.entities.Video;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class VideoUUIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (object instanceof Video) {
            Video video = (Video) object;
            String source = video.getChannel().getName() + video.getTitle();
            return getSHA256(source);
        }
        return UUID.randomUUID().toString();
    }

    private String getSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString().substring(0, 40);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}