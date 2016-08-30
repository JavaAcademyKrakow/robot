package io.github.bookcrawler.entities;

public class BookInfoBuilder {

    String title;
    String author;
    String description;
    String price;
    String library;
    String url;

    public BookInfoBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BookInfoBuilder author(String author) {
        this.author = author;
        return this;
    }

    public BookInfoBuilder description(String description) {
        this.description = description;
        return this;
    }


    public BookInfoBuilder price(String price) {
        this.price = price;
        return this;
    }

    public BookInfoBuilder library(String library) {
        this.library = library;
        return this;
    }

    public BookInfoBuilder url(String url) {
        this.url = url;
        return this;
    }

    public BookInfo build() {
        return new BookInfo(this);
    }
}
