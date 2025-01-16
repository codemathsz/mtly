package com.codemathsz.mtly.controllers;

import com.codemathsz.mtly.dtos.CreateUrlDTO;
import com.codemathsz.mtly.models.Url;
import com.codemathsz.mtly.services.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlService service;

    @GetMapping("/{keyUrl}")
    public void get(@PathVariable String keyUrl, HttpServletResponse response){
        var url = this.service.getOriginalUrl(keyUrl);
        try {
            if (url.getExpiredAt().isBefore(LocalDateTime.now())) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "link expirado");
            } else {
                response.sendRedirect(url.getOriginalUrl());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/createUrl")
    public ResponseEntity<Url> create(@RequestBody CreateUrlDTO originalUrl){
        return this.service.createShortUrl(originalUrl);
    }

}