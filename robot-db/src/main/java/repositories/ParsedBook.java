package repositories;


import domain.CategoryName;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

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
}
