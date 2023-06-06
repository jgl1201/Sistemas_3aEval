package com.cebem.RickAndMorty.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.RickAndMorty.Services.RickAndMortyService;
import com.cebem.RickAndMorty.models.CharacterModel;
import com.cebem.RickAndMorty.models.CharactersModel;
import com.cebem.RickAndMorty.utils.Utils;

@RestController
@CrossOrigin(origins = "*")
public class RickController {
    // http://127.0.0.1/
    // http://localhost/
    // http://angel.com/

    @GetMapping("/")
    public String saluda() {
        return "Bienvenid@ a mi api rest de RickAndnMorty";
    }

    @GetMapping("/random")
    public String random() {
        return Math.round(Math.random() * 10 ) + "";
    }

    // http://localhost:8080/palindrome/word
    @GetMapping("/palindrome/{word}")
    public String palindrome(@PathVariable String word) {
        return Utils.isPalindrome(word) ? 
            "Si es palindromo" : "No es palindromo";
    }

    // http://localhost:8080/add/2/4
    // http://localhost:8080/add?n1=2&n2=4
    @GetMapping ("/add")
    public String add(@RequestParam String n1, @RequestParam String n2) {
        float result = Float.parseFloat(n1) + Float.parseFloat(n2);
        Object params[] = {n1, n2, result};
        return MessageFormat.format("La suma de {0} y {1} es igual a {2}", params);
    }
    
    @PostMapping("/saveOnDisk")
    public String saveOnDisk(@RequestParam Map<String, String> body) {
        String name = body.get("name");
        String price = body.get("price");

        System.out.println(name + "-" + price);

        String info = name + " " + price + "\n";
        try {
            Utils.writeOnDisk("datos.txt", info);
        } catch (IOException e) {
            return "Error al intentar escribir el fichero";
        }

        return "Gracias por enviar el formulario, los datos se han enviado correctamente.";
    }

    @DeleteMapping("/deleteFromDisk")
    public String removeFromDisk() {
        boolean r = Utils.deleteFile("datos.txt");
        return r ? "Borrado correctamente" : "Error al borrar";
    }

    //Crear un endPoint que devuelva el cuadrado de un numero pasado
    @GetMapping("/square")
    public String square(@RequestParam String number) {
        try {
            float result = (float)(Math.pow(Float.parseFloat(number), 2));
            return "El cuadrado de " + number + " es: " + result;
        } catch(NumberFormatException e) {
            return "El numero no es valido";
        }
    }

    /* Crear un endPoint que borre la info de datos.txt, no el fichero
    Probar el funcionamiento mediante conexion Thunderclient */
    @DeleteMapping("/deleteData")
    public String deleteData() {
        try {
            Utils.deleteData("datos.txt");
            return "Fichero limpiado correctamente";
        } catch (IOException e) {
            return "Error al limpiar el fichero: " + e.getMessage();
        }
    }

    /* Crear un endPoint que devuelva toda la informacion de datos.txt */
    @GetMapping("products")
    public static  String getProducts() {
       try {
        return Utils.readFromDiks("datos.txt");
        } catch (FileNotFoundException e) {
        return "Fichero no encontrado";
        } catch (IOException e) {
        return "Error en la lectura del fichero: " + e.getMessage();
        }
    }
    
    /* Crear un endPoint que devuelva el mayor de 3 numeros pasados
     * Si algunpo de los parametros pasados no es un numero, 
     * retornar "ERROR" */
    @GetMapping ("/highest")
    public String highest(@RequestParam String n1, @RequestParam String n2,
        @RequestParam String n3) throws Exception {
        float f1 = Float.parseFloat(n1);
        float f2 = Float.parseFloat(n2);
        float f3 = Float.parseFloat(n3);
        return Utils.maxOfElements(f1, f2, f3) + "";
        
    }

     /* Crear un endPoint al que se le pase una frase.
      * Devolver la misma frase, pero con la primera
      letra de cada palabra en mayuscula */
    @GetMapping("/capitalize/{text}")
    public String capitalize(@PathVariable String text) {
        return Utils.capitalizeText(text);
    }

      /* Crea un endPoint que devuelva 3 colores random sin
       * repetir. Parte de un array de colores basicos
       * [negro, azul, marron, gris, verde, naranja, rosa, purpura, rojo,
       * blanco, amarillo] */
      @GetMapping ("/randomColor")
      public String randomColor() {
        final int  COLOR_COUNT = 3;
        final String[] COLORS = {"negro", "azul", "marron", "gris", "verde", "naranja",
          "rosa", "purpura", "rojo", "blanco", "amarillo"};
        if (COLOR_COUNT > COLORS.length) throw new RuntimeException("Limite sobrepasado");
        
        ArrayList<String> returnColors = new ArrayList<String>(Arrays.asList(COLORS));
        String finalColors = "";
        for (int i = 0; i < COLOR_COUNT; i++) {
            int random = Utils.generateRandom(returnColors.size());
            finalColors += returnColors.remove(random) + " ";
        }

        return finalColors;
      }

    /* Crear un endPoint que devuelva
     * un personaje random de la serie 
     * GET https://rickandmortyapi.com/api/character/??? */
      @Autowired
      RickAndMortyService rickAndMortyService;

      @GetMapping("/rickandmorty/random")
      public String randomCharacter() {
       // RickAndMortyService rickAndMortyService = new RickAndMortyService();
        CharacterModel characterModel = rickAndMortyService.getRandomCharacter();

        return characterModel.name + "<br/>" + "<img width='200' src='" + characterModel.image + "'/>";
      }

      @GetMapping("/rickandmorty/allCharacters")
      public String allCharacters() {
        CharactersModel charactersModel = rickAndMortyService.getAllCharacters();
        String html = "";
        for (CharacterModel cm : charactersModel.results) {
            html += cm.name;
            html += "<br/>";
            html += "<img width='200px' src='" + cm.image + "'>";
            html += "<hr>";
        }
        return html;
      }

}
