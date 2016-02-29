package ru.photoparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.photoparser.entity.Album;
import ru.photoparser.service.AlbumService;

@Controller
@RequestMapping("/album")
public class AlbumController {


    @Qualifier("albumService")
    @Autowired
    private AlbumService albumService;


    @RequestMapping(method = RequestMethod.GET)
    public String getAlbumById(@RequestParam("id") int id, ModelMap model){
        Album album = albumService.getById(id);
        model.addAttribute("album", album);
        return "home";
    }
}

