package ru.photoparser.parse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.photoparser.parse.impl.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ParserConfig {


    @Qualifier("afistfullofboltsParser")
    @Autowired
    private AfistfullofboltsParser afistfullofboltsParser;
    @Qualifier("edpeersParser")
    @Autowired
    private EdpeersParser edpeersParser;
    @Qualifier("erichmcveyParser")
    @Autowired
    private ErichmcveyParser erichmcveyParser;
    @Qualifier("jeffascoughParser")
    @Autowired
    private JeffascoughParser jeffascoughParser;
    @Qualifier("kellyandsergioParser")
    @Autowired
    private KellyandsergioParser kellyandsergioParser;
    @Qualifier("levkupermanParser")
    @Autowired
    private LevkupermanParser levkupermanParser;
    @Qualifier("loveisabigdealParser")
    @Autowired
    private LoveisabigdealParser loveisabigdealParser;
    @Qualifier("ryanbrenizerParser")
    @Autowired
    private RyanbrenizerParser ryanbrenizerParser;
    @Qualifier("tinydotphotographyParser")
    @Autowired
    private TinydotphotographyParser tinydotphotographyParser;
    @Qualifier("twomannParser")
    @Autowired
    private TwomannParser twomannParser;
    @Qualifier("tylerbranchphotoParser")
    @Autowired
    private TylerbranchphotoParser tylerbranchphotoParser;


    public ParserConfig() {
    }

    @Bean
    public List<Parser> parserCollection(){
        List<Parser> list = new ArrayList<>();
        list.add(afistfullofboltsParser);
        list.add(edpeersParser);
        list.add(erichmcveyParser);
        list.add(jeffascoughParser);
        list.add(kellyandsergioParser);
        list.add(levkupermanParser);
        list.add(loveisabigdealParser);
        list.add(ryanbrenizerParser);
        list.add(tinydotphotographyParser);
        list.add(twomannParser);
        list.add(tylerbranchphotoParser);
        return list;
    }

}
