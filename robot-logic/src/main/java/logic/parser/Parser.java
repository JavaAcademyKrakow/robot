package logic.parser;

import domain.CategoryName;
import repositories.ParsedBook;

import java.util.List;
import java.util.Optional;

/**
 * Parser interface - the main goal of the interface is to parse the data for current category
 * (obtained by JSOUP from the Internet).
 */
public interface Parser {

    /**
     * Main parser method. It performs crawling particular website to obtain discount books data.
     *
     * @return - Optional with the ist of books on the discount found on the web page. The optional contains empty list
     * when there are no books on discount. Moreover, null is wrapped (empty optional), when the web page
     * is not valid (i.e. it does not contain any books).
     */
    Optional<List<ParsedBook>> parse();

    /**
     * Method to set the link to the web page that will be parsed.
     *
     * @param link - URI of the web page
     * @return - Parser (fluent)
     */
    Parser setLink(String link);

    /**
     * Setter of category of the current book.
     *
     * @param category - current CategoryName (enum)
     * @return - Parser (fluent)
     */
    Parser setCategory(CategoryName category);
}