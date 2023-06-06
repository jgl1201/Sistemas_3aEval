package com.cebem.RickAndMorty.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebem.RickAndMorty.models.GameModel;
import com.cebem.RickAndMorty.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    GameRepository gameRepository;

    public ArrayList<GameModel> getAllMemes() {
        return (ArrayList<GameModel>) (gameRepository.findAll());
    }

    public GameModel createGame(GameModel game) {
        return gameRepository.save(game);
    }

     public boolean deleteGame(long id) {
        try {
            gameRepository.deleteById(id);
            return true;
        } catch(IllegalArgumentException ex) {
            return false;
        }
    }

}
