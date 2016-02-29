package ru.photoparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.service.PortfolioService;

import java.util.List;

@SessionAttributes(value = "portfolioList")
@Controller
@RequestMapping("/portfolio")
public class PortfolioController {
    @Qualifier("portfolioService")
    @Autowired
    private PortfolioService portfolioService;


    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String getAllPortfolio(ModelMap model) {

        List<Portfolio> portfolioList = portfolioService.getAll();
        if(portfolioList != null) {
            model.addAttribute("portfolioList", portfolioList);
        }
        return "index";
    }

}
