package com.codemathsz.mtly.services;

import com.codemathsz.mtly.dtos.CreateUrlDTO;
import com.codemathsz.mtly.models.Url;
import com.codemathsz.mtly.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UrlService {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    private UrlRepository repository;

    public Url getOriginalUrl(String keyUrl){
        String shortUrl = BASE_URL+keyUrl;
        return this.repository.findByShortUrl(shortUrl).orElseThrow( () -> new RuntimeException("Url não encontrada"));
    }

    public ResponseEntity<Url> createShortUrl(CreateUrlDTO createUrlDTO){
        var shortUrl = generateShortUrl();
        var newUrl = Url.builder()
                .shortUrl(shortUrl)
                .originalUrl(createUrlDTO.originalUrl())
                .expiredAt(LocalDateTime.now().plus(Duration.ofMinutes(2)))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(newUrl));
    }

    private String generateShortUrl() {
        String shortUrl;
        boolean shortUrlExists;
        do{
            StringBuilder sb = new StringBuilder(6);

            for (int i = 0; i < 6; i++) {
                sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
            }
            shortUrl = BASE_URL+sb.toString();
            shortUrlExists = this.repository.findByShortUrl(shortUrl).isPresent();
        }while (shortUrlExists);

        return shortUrl;
    }
}
