package services.controllers;

import domain.Books;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.model.response.BooksResponse;

import java.util.Arrays;
import java.util.List;

@RestController
public class SelectCategories {

    @RequestMapping(method = RequestMethod.GET, value = "books")
    public BooksResponse setCategories(@RequestParam(value = "books")List<String> categories) {
        return new BooksResponse(Arrays.asList(new Books(1, "first"), new Books(2, "second"), new Books(3, "third"), new Books(4, "fourth"),
                new Books(5, "first"), new Books(6, "second"), new Books(7, "third"), new Books(8, "fourth"),
                new Books(9, "first"), new Books(10, "second"), new Books(11, "third"), new Books(12, "fourth")));
    }
}
