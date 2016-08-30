package parser;


import domain.CategoryName;
import repositories.ParsedBook;

import java.util.List;
import java.util.Optional;

/**
 * Parser interface - the main goal of the interface is to parse the data for current category
 * (obtained by JSOUP from the Internet).
 */
public interface Parser {

    Optional<List<ParsedBook>> parse();
    Parser setLink(String link);

    Parser setCategory(CategoryName category);
}