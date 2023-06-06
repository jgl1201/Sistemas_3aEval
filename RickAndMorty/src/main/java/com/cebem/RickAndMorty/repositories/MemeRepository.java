package com.cebem.RickAndMorty.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cebem.RickAndMorty.models.MemeModel;

@Repository
public interface MemeRepository extends CrudRepository<MemeModel, Long> {
    
}
