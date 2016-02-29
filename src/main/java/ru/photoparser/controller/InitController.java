package ru.photoparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.service.PortfolioService;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.List;

@SessionAttributes(value = "portfolioList")
@Controller
public class InitController {
    @Qualifier("portfolioService")
    @Autowired
    private PortfolioService portfolioService;
    @Qualifier("servletContext")
    @Autowired
    private ServletContext context;


    @PostConstruct
    public void init(){
        List<Portfolio> portfolioList = portfolioService.getAll();
        context.setAttribute("portfolioList",portfolioList);
    }
}
