package com.codemathsz.mtly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String originalUrl;
    private String shortUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}
