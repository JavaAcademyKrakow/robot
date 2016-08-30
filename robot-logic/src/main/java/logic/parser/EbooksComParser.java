package logic.parser;

import logic.book.Book;
import logic.book.Category;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static logic.book.Book.*;
import static org.jsoup.Jsoup.*;

/**
 * Parser implementation for ebooks.com.
 * Warning: the code is a bit imperative - it might be reworked later.
 */
@Slf4j
public class EbooksComParser implements Parser {

    private Document rootDocument;
    private BookBuilder bookBuilder;
    private Category category;
    private String link;

    @Override
    public Parser setLink(String link) {
        this.link = link;
        return this;
    }

    @Override
    public Parser setCategory(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public List<Book> parse() {
        List<Book> resultList = new LinkedList<>();


        try {
            rootDocument = openDocument();
            Elements booksFound = findBooks();
            if ("".equals(booksFound.text())) {
                return null;
            }


            for (Element e : booksFound) {

                // old price - to check if it is on discount
                String old = e.select("div.additional-info > span > span").select("[style*=text-decoration:line-through]").text();

                if ("".equals(old)) {
                    continue;
                }

                // variables
                String title;
                String printHouse;
                String description;
                String currency;
                List<String> authors = new LinkedList<>();
                short year;

                // currency
                currency = String.valueOf(old.charAt(0));

                // old price
                float oldPrice = Float.parseFloat(old.replace(currency, "").trim());

                // new price
                float newPrice = Float.parseFloat(e.select("div.additional-info > span > span").text().replace(old, "").replace(currency, "").trim());

                // title
                title = e.select("h4 > span.logic.book-title > a").text();

                // find authors
                Elements authorsSet = e.select("h4 > span.author > a");
                authorsSet.forEach(author -> authors.add(author.text()));

                // print house
                printHouse = e.select("div.additional-info > span").first().text();

                // year
                year = Short.valueOf(e.select("div.additional-info > span").first().nextElementSibling().text().replace(";", ""));

                // description
                String descriptionLink = e.select("p > a").first().attr("abs:href");
                Document descriptionDoc = connect(descriptionLink).timeout(0).get();
                description = descriptionDoc.select("div.short-description").select("[itemprop]").text();


                bookBuilder = builder().title(title).authors(authors).printHouse(printHouse).year(year).currency(currency)
                        .oldPrice(oldPrice).newPrice(newPrice).description(description).link(link).category(category);

                resultList.add(bookBuilder.build());
            }

        } catch (IOException e) {
            log.debug("IOException caught", e);
            log.error("IOException caught", e);
        }
        return resultList;
    }

    Document openDocument() throws IOException {
        return connect(link).timeout(0).get();
    }

    private Elements findBooks() {
        return rootDocument.select("li.search-row.clearfix");
    }
}