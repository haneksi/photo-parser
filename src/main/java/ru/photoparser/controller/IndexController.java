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

import java.util.ArrayList;
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
        Portfolio portfolio = edpeersParser.getPortfolio();
        List<Album> albums = portfolio.getAlbums();
        List<Image> images = new ArrayList<>();
        for (Album album : albums) {
            images.addAll(album.getImages());
        }

        model.addAttribute("images",  images);

//        portfolioService.create(portfolio);

        Portfolio byId = portfolioService.getById(9);

        return "index";
    }
}
