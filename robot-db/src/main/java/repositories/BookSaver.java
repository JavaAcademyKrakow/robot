package repositories;

import dao.BookDAO;
import domain.Author;
import domain.Book;
import domain.Category;
import domain.PrintHouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link BookSaver} saves book into the database
 */
@Repository
@Slf4j
public class BookSaver {

    @Autowired
    private AuthorManager authorManager;

    @Autowired
    private CategoryManager categoryManager;

    @Autowired
    private PrintHouseManager printHouseManager;

    @Autowired
    private BookDAO bookDAO;

    /**
     * saves an {@link ParsedBook} into the database to achieve the goal
     * it uses {@link AuthorManager}, {@link CategoryManager} and {@link PrintHouseManager}
     * @param parsedBook - {@link ParsedBook} all Information about the book.
     */
    public void save (ParsedBook parsedBook) {
        Category category = categoryManager.getCategory(parsedBook.getCategory());
        PrintHouse printHouse = printHouseManager.getPrintHouse(parsedBook.getPrintHouse());
        List<Author> authors = parsedBook.getAuthors()
                .stream()
                .map(authorManager::getAuthorForName)
                .collect(Collectors.toList());
        Book book = Book.builder()
                .title(parsedBook.getTitle())
                .category(category)
                .authors(authors)
                .currency(parsedBook.getCurrency())
                .description(parsedBook.getDescription())
                .printHouse(printHouse)
                .link(parsedBook.getLink())
                .newPrice(parsedBook.getNewPrice())
                .oldPrice(parsedBook.getOldPrice())
                .year(parsedBook.getYear())
                .build();
        bookDAO.save(book);
    }
}