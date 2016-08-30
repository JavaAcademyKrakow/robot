package logic.parser;

import logic.book.Book;
import logic.book.Category;

import java.util.List;

/**
 * Parser interface - the main goal of the interface is to parse the data for current category
 * (obtained by JSOUP from the Internet).
 */
public interface Parser {
    /**
     * Main parser method. It performs crawling particular website to obtain discount books data.
     * @return - List of books on the discount found on the web page. The method returns empty list
     *          when there are no books on discount. Moreover, null is returned, when the web page
     *          is not valid (i.e. it does not contain any books).
     */
    List<Book> parse();

    /**
     * Method to set the link to the web page that will be parsed.
     * @param link - URI of the wep page
     * @return - Parser (fluent)
     */
    Parser setLink(String link);

    /**
     * Setter of category of the current book.
     * @param category - current Category (enum)
     * @return - Parser (fluent)
     */
    Parser setCategory(Category category);
}