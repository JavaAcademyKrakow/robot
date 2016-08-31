package services.controllers;

import dao.BookDAO;
import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.model.response.BooksResponse;

import java.util.List;

@RestController
public class SelectCategories {

   @Autowired
    private BookDAO bookDAO;

    @RequestMapping(method = RequestMethod.GET, value = "books")
    public BooksResponse setCategories(@RequestParam(value = "books")List<String> categories) {
        List<Book> books = bookDAO.findAll(new PageRequest(1,12)).getContent();
        for(Book b : books) {
            System.out.println("##########################          " + b);
        }

        return new BooksResponse(books);
    }
}
