package io.github.bookcrawler.entities;

public class BookInfo {


    private String title;
    private String author;
    private String description;
    private String price;
    private String library;
    private String url;

    public BookInfo(BookInfoBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.description = builder.description;
        this.price = builder.price;
        this.library = builder.library;
        this.url = builder.url;

    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + description.substring(0, 40) + "... | " + price + " | " + library + " - " + url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getLibrary() {
        return library;
    }

    public String getUrl() {
        return url;
    }

}
