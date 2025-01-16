package com.codemathsz.mtly.repositories;

import com.codemathsz.mtly.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID> {
}
