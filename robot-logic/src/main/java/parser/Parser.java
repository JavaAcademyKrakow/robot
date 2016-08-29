package parser;


import domain.Book;
import domain.CategoryName;

import java.util.List;
import java.util.Optional;

/**
 * Parser interface - the main goal of the interface is to parse the data for current category
 * (obtained by JSOUP from the Internet).
 */
public interface Parser {
    Optional<List<Book>> parse();
    Parser setLink(String link);
    Parser setCategory(CategoryName category);
}