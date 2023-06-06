package com.cebem.RickAndMorty.Controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.RickAndMorty.Services.MemeService;
import com.cebem.RickAndMorty.models.MemeModel;

@Controller
public class MemeController {

    @Autowired
    MemeService memeService;

    @RequestMapping("/memes")
    public String listAllMemes(Model model) {
        ArrayList<MemeModel> memes = memeService.getAllMemes();
        model.addAttribute("misMemes", memes);
        return "memelist";
    }

    // se pueden llamar igual porque los metodos de acceso son diferentes
    @PostMapping("/memes")
    public String memesAdd(@RequestParam Map<String, String> body) {
        String description = body.get("description");
        String url = body.get("url");
        String category = body.get("category");
        String author = body.get("author");

        MemeModel newMeme = new MemeModel();
        newMeme.setDescription(description);
        newMeme.setUrl(url);
        newMeme.setCategory(category);
        newMeme.setAuthor(author);

        memeService.createMeme(newMeme);
        return "memeok";
    }

    @RequestMapping("/memes/addForm")
    public String addMemeForm() {
        return "memeaddform";
    }

}
