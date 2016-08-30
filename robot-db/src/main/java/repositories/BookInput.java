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

@Repository
@Slf4j
public class BookInput {

    @Autowired
    AuthorInput authorInput;

    @Autowired
    CategoryInput categoryInput;

    @Autowired
    PrintHouseInput printHouseInput;

    @Autowired
    BookDAO bookDAO;

    public void save(ParsedBook parsedBook) {
        log.info("staring operations on database");
        Category category = categoryInput.saveCategory(parsedBook.getCategory());
        PrintHouse printHouse = printHouseInput.savePrintHouse(parsedBook.getPrintHouse());
        List<Author> authors = parsedBook.getAuthors().stream().map(name -> authorInput.saveAuthor(name)).collect(Collectors.toList());
        Book book = Book.builder().title(parsedBook.getTitle()).category(category).authors(authors).currency(parsedBook.getCurrency())
                .description(parsedBook.getDescription()).printHouse(printHouse).link(parsedBook.getLink())
                .newPrice(parsedBook.getNewPrice()).oldPrice(parsedBook.getOldPrice()).year(parsedBook.getYear()).build();
        bookDAO.save(book);
        log.info("saving book" + book);
    }
}

