package services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.converter.BookConverter;
import services.model.response.BooksResponse;

import java.util.List;

@RestController
public class SelectCategories {

   @Autowired
    private BookConverter bookConverter;

    @RequestMapping(method = RequestMethod.GET, value = "categories")
    public BooksResponse setCategories(@RequestParam(value = "categories")List<String> categories,
                                        @RequestParam(value = "pageNumber") int pageNumber) {

        return new BooksResponse(bookConverter.getBooksFromPage(pageNumber));
    }
}
