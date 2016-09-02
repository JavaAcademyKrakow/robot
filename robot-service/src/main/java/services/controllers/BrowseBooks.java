package services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.converter.BookConverter;
import services.model.response.BooksResponse;

@RestController
public class BrowseBooks {

    @Autowired
    BookConverter bookConverter;

    @RequestMapping(method = RequestMethod.GET, value = "numberofpages")
    public int numberOfPages() {
        return bookConverter.numberOfPages();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getbooksfrompage")
    public BooksResponse getBooksFromPage(@RequestParam (value = "pageNumber")int pageNumber) {
        return new BooksResponse(bookConverter.getBooksFromPage(pageNumber));
    }
}
