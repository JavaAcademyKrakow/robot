package services.converter;

import dao.BookDAO;
import domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repositories.ParsedBook;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookConverter {

    @Autowired
    private BookDAO bookDAO;

    public List<ParsedBook> getBooksFromPage(int pageNumber) {
        return  bookDAO.findAll(new PageRequest(pageNumber, 12)).getContent().stream().map(book -> ParsedBook.builder().
                authors(book.getAuthors().stream().map(Author::getName).collect(Collectors.toList()))
                .description(book.getDescription())
                .year(book.getYear())
                .category(book.getCategory().getName())
                .title(book.getTitle())
                .printHouse(book.getPrintHouse().getName())
                .currency(book.getCurrency())
                .link(book.getLink())
                .newPrice(book.getNewPrice())
                .oldPrice(book.getOldPrice())
                .build())
                .collect(Collectors.toList());
    }

    public int numberOfPages() {
        return (int)bookDAO.count()/12 + 1;
    }
}
