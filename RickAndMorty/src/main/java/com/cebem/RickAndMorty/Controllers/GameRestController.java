package com.cebem.RickAndMorty.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.RickAndMorty.Services.GameService;

@RestController
public class GameRestController {
    
    @Autowired
    GameService gameService;

    @DeleteMapping("/games/{id}")
    public String deleteGame(@PathVariable String id) {
        boolean result = gameService.deleteGame(Long.parseLong(id));
        if (result) return "OK";
        else return "ERROR";
    }
}
