package ru.photoparser.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.parse.EdpeersParserImpl;
import ru.photoparser.service.PortfolioService;

import java.util.List;

@Controller
public class IndexController {


    @Qualifier("edpeersParser")
    @Autowired
    private EdpeersParserImpl edpeersParser;

    @Qualifier("portfolioService")
    @Autowired
    private PortfolioService portfolioService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        List<Image> images = edpeersParser.getAllImages();
        model.addAttribute("images",  images);

//        System.out.println(edpeersParser.getPortfolio().getAuthor());

//        portfolioService.create(edpeersParser.getPortfolio());

        Portfolio id = portfolioService.getById(171);
        for (Album album : id.getAlbums()) {
            System.out.println(album.getId().toString());
            System.out.println(album.getAuthor().toString());
            System.out.println(album.getPortfolio().getId().toString());
        }

        return "index";
    }
}
