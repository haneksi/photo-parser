package ru.photoparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.service.PortfolioService;

import java.util.Iterator;
import java.util.List;

@Controller
public class IndexController {

    @Qualifier("portfolioService")
    @Autowired
    private PortfolioService portfolioService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        List<Portfolio> portfolioList = portfolioService.getAll();
        model.addAttribute("portfolioList", portfolioList);
        return "index";
    }
}
