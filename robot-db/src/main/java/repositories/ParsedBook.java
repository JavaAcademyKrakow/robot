package repositories;


import domain.CategoryName;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

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
