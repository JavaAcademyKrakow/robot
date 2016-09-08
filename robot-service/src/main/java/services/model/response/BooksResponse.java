package services.model.response;

import repositories.ParsedBook;

import java.util.List;

/**
 * Created by daniel on 31.08.16.
 */
public class BooksResponse {

    /**
     * list of books that we want to check when we profile our user
     */
    public final List<ParsedBook> books;

    /**
     * We set final variable here
     * @param books)
     */
    public BooksResponse(List<ParsedBook> books) {
        this.books = books;
    }
}
