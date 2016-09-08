package repositories;


import domain.CategoryName;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

import static java.lang.Math.abs;

/**
 * Builder for Books which is needed to save all information about the book into correct entities by {@link BookSaver}
 */
@Builder
public class ParsedBook {
    @Getter
    private final String title;
    @Getter
    private final String printHouse;
    @Getter
    private final short year;
    @Getter
    private final List<String> authors;
    @Getter
    private final float oldPrice;
    @Getter
    private final float newPrice;
    @Getter
    private final String currency;
    @Getter
    private final CategoryName category;
    @Getter
    private final String description;
    @Getter
    private final String link;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(title)
                .append(printHouse)
                .append(year)
                .append(authors)
                .append(oldPrice)
                .append(newPrice)
                .append(category)
                .append(currency)
                .append(description)
                .append(link)
                .toHashCode();
    }

    @Override
    public boolean equals(Object bookObj) {
        if (this == bookObj)
            return true;
        if (bookObj == null || getClass() != bookObj.getClass())
            return false;
        ParsedBook book = (ParsedBook)bookObj;
        return title.equals(book.title) && printHouse.equals(book.printHouse) && year == book.year
                && authors.equals(book.authors) && abs(oldPrice - book.oldPrice) < 1e-10
                && abs(newPrice - book.newPrice) < 1e-10 && category == book.category
                && currency.equals(book.currency) && description.equals(book.description)
                && link.equals(book.link);
    }
}