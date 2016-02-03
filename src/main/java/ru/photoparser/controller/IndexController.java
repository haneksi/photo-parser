package ru.photoparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.parse.EdpeersParserImpl;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private EdpeersParserImpl edpeersParser;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        List<Image> images = edpeersParser.getAllImages();
        model.addAttribute("images",  images);
        return "index";
    }
}
