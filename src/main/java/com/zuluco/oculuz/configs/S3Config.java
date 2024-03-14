package com.zuluco.oculuz.configs;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${aws.access_key_id}")
    private String accessKey;

    @Value("${aws.secret_access_key}")
    private String secretKey;

    // Обратите внимание: aws.region не используется, так как мы устанавливаем конкретный endpoint
    // @Value("${aws.region}")
    // private String region;

    @Bean
    public AmazonS3 s3client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setSignerOverride("AWSS3V4SignerType"); // Это может потребоваться для некоторых S3-совместимых сервисов

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://s3.timeweb.cloud", "ru-1"))
                .withPathStyleAccessEnabled(true) // Используйте path-style доступ, если это требуется вашим S3 сервисом
                .withClientConfiguration(clientConfig)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .disableChunkedEncoding() // Отключите chunked encoding, если это вызывает проблемы
                .build();
    }
}
