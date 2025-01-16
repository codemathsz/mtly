package com.codemathsz.mtly.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UrlController {

    @GetMapping("")
    public String get(){
        return "Hello Mtly";
    }
}
// mtly.com/{}