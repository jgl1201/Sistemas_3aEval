package com.cebem.RickAndMorty.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.RickAndMorty.Services.MemeService;

@RestController
public class MemeRestController {

    @Autowired
    MemeService memeService;
    
    // http://localhost:8080/memes/id
    @DeleteMapping("/memes/{id}")
    public String memesDelete(@PathVariable String id) {
        boolean result = memeService.deleteMeme(Long.parseLong(id));
        if (result) return "OK";
        else return "ERROR";
    }
    
}
