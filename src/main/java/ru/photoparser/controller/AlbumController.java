package ru.photoparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.photoparser.entity.Album;
import ru.photoparser.service.AlbumService;

@Controller
public class AlbumController {


    @Qualifier("albumService")
    @Autowired
    private AlbumService albumService;


    @RequestMapping(value = "/album/${id}", method = RequestMethod.GET)
    public String getAlbum(@PathVariable Integer id, ModelMap model){
        Album album = albumService.getById(id);
        model.addAttribute("album", album);
        return "album";
    }
}

