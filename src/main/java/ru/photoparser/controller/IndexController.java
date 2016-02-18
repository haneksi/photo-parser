package ru.photoparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.parse.Parser;
import ru.photoparser.service.PortfolioService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Qualifier("portfolioService")
    @Autowired
    private PortfolioService portfolioService;

    @Qualifier("twomannParser")
    @Autowired
    private Parser parser;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        Portfolio portfolio = parser.parsing();
        List<Album> albums = portfolio.getAlbums();
        List<Image> images = new ArrayList<>();
        for (Album album : albums) {
            images.addAll(album.getImages());
        }

        model.addAttribute("images",  images);

        portfolioService.create(portfolio);

        return "index";
    }
}
