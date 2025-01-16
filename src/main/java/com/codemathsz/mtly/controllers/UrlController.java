package com.codemathsz.mtly.controllers;

import com.codemathsz.mtly.dtos.CreateUrlDTO;
import com.codemathsz.mtly.models.Url;
import com.codemathsz.mtly.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlService service;

    @GetMapping("")
    public String get(){
        return "Hello Mtly";
    }

    @PostMapping("/createUrl")
    public ResponseEntity<Url> create(@RequestBody CreateUrlDTO originalUrl){
        return this.service.createShortUrl(originalUrl);
    }

}