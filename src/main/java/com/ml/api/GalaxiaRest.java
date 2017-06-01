package com.ml.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.service.GalaxiaService;

@RestController
public class GalaxiaRest {

    @Autowired
    public GalaxiaService service;

    @RequestMapping("/")
    public String index() {
        return "Hola wacho! " + service.hola();
    }
}
