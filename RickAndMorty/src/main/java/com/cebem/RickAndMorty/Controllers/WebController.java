package com.cebem.RickAndMorty.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cebem.RickAndMorty.Services.RickAndMortyService;
import com.cebem.RickAndMorty.models.CharactersModel;

@Controller
public class WebController {

    @Autowired
    RickAndMortyService rickAndMortyService;


    @RequestMapping("/rickandmorty/alltemplate")
    public String charactersTemplate(Model modelo) {
        CharactersModel charactersModel = rickAndMortyService.getAllCharacters();

        modelo.addAttribute("creator", "Creado por Jorge");
        modelo.addAttribute("characters", charactersModel.results);

        return "rickandmortyall";
    }

    // Queremos mostrar en una pagina web el numero total de personajes
    // Aparecera la frase " TOTALO DE PERSONAJES: " centrado y verde
    // Usa un motor de plantillas
    @RequestMapping("/rickandmorty/numbercharacters")
    public String numberCharacters(Model modelo) {
        int numberCharacters = rickAndMortyService.getNumberCharacters();

        modelo.addAttribute("number", numberCharacters);

        return "rickandmortynumber";
    }
}