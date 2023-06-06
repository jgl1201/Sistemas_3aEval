package com.cebem.RickAndMorty.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cebem.RickAndMorty.models.GameModel;

@Repository
public interface GameRepository extends CrudRepository<GameModel, Long> {
    
}
