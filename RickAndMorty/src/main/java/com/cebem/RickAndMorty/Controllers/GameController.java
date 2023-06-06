package com.cebem.RickAndMorty.Controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.RickAndMorty.Services.GameService;
import com.cebem.RickAndMorty.models.GameModel;

@Controller
public class GameController {
    
    @Autowired
    GameService gameService;

    @RequestMapping("/games")
    public String listAllGames(Model model) {
        ArrayList<GameModel> games = gameService.getAllMemes();
        model.addAttribute("myGames", games);
        return "gamelist";
    }

    @PostMapping("/games")
    public String addGames(@RequestParam Map<String, String> body) {
        String url = body.get("description");
        String companyName = body.get("companyName");
        String category = body.get("companyName");
        String description = body.get("description");
        String author = body.get("author");
        
        GameModel newGame = new GameModel();
        newGame.setUrl(url);
        newGame.setCompanyName(companyName);
        newGame.setCategory(category);
        newGame.setDescription(description);
        newGame.setAuhtor(author);

        gameService.createGame(newGame);

        return "gameok";
    }

    @RequestMapping("/games/addForm")
    public String addgameForm() {
        return "gameaddform";
    }

}
