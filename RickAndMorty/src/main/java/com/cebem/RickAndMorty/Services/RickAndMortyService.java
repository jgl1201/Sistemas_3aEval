package com.cebem.RickAndMorty.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.RickAndMorty.models.CharacterModel;
import com.cebem.RickAndMorty.models.CharactersModel;
import com.cebem.RickAndMorty.models.NumberCharacters;
import com.cebem.RickAndMorty.utils.Utils;

@Service
public class RickAndMortyService {

    @Autowired
    RestTemplate restTemplate;

    public CharacterModel getRandomCharacter() {
        final int TOTAL_CHARACTERS = 826;
        int random = Utils.generateRandom(TOTAL_CHARACTERS - 1) + 1;
        String url = "https://rickandmortyapi.com/api/character/" + random;

        /* libreria para hacer una peticion a un api y recoger la respuesta */
        //RestTemplate restTemplate = new RestTemplate();
        CharacterModel datos = restTemplate.getForObject(url, CharacterModel.class);

        return datos;
    }

    public CharactersModel getAllCharacters() {
        String url = "https://rickandmortyapi.com/api/character/";
        CharactersModel datos = restTemplate.getForObject(url, CharactersModel.class);

        return datos;
    }

    public int getNumberCharacters() {
        String url = "https://rickandmortyapi.com/api/character/";
        NumberCharacters datos = restTemplate.getForObject(url, NumberCharacters.class);
        int count = datos.getInfo().getCount();

        return count;
    }

}
