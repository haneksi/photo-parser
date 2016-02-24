package ru.photoparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.parse.Parser;
import ru.photoparser.parse.impl.ErichmcveyParser;
import ru.photoparser.service.PortfolioService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Qualifier("portfolioService")
    @Autowired
    private PortfolioService portfolioService;

//    @Qualifier("tinydotphotographyParser")
//    @Autowired
//    private Parser parser;

    @Resource(name = "parserCollection")
    private List<Parser> list;



    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        for (Parser parser : list) {
            Portfolio portfolio = parser.parsing();
            portfolioService.create(portfolio);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        Portfolio parsing = parser.parsing();
//        portfolioService.create(parsing);

        return "index";
    }
}
