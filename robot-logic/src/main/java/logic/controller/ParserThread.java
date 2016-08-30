package logic.controller;



import domain.CategoryName;
import logic.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import repositories.ParsedBook;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Parser thread - launches parser and shows the results
 */
@Slf4j
final class ParserThread implements Callable<Optional<List<ParsedBook>>> {

    private final Parser parser;
    private final String link;
    private final CategoryName category;

    ParserThread(Class<? extends Parser> parserClass, CategoryName category, String link) {
        parser = createParser(parserClass);
        this.link = link;
        this.category = category;
    }

    private static Parser createParser(Class<? extends Parser> parserClass) {
        Parser parser = null;

        try {
            parser = parserClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            log.debug("Exception found", e);
            Thread.currentThread().interrupt();
        }

        return parser;
    }

    @Override
    public Optional<List<ParsedBook>> call() throws Exception {
        parser.setLink(link).setCategory(category);
        return parser.parse();
    }
}