package domain;

import lombok.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;


/**
 * {@link Book} is representation of book in a database.
 * There are parameters related to book and price.
 * Field description has a {@code @Lob} annotation because the maximum 255 chars is not enough.
 * There are also mappings {@code @ManyToOne } to {@link PrintHouse}, {@link Category}  and  {@code @ManyToMany} To {@link Author}
 * The instance of book can be created by a builder.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private long bookID;

    @Getter
    @Setter
    @Column
    private String title;

    @Getter
    @Setter
    @Column(name = "new_price")
    private float newPrice;

    @Getter
    @Setter
    @Column(name = "old_price")
    private float oldPrice;

    @Getter
    @Setter
    @Column(length = 3)
    private String currency;

    @Getter
    @Setter
    private short year;

    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    @Lob
    private String description;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "print_houseID")
    private PrintHouse printHouse;

    @ManyToMany(fetch = FetchType.EAGER)
    @Setter
    @Getter
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    @Setter
    @Getter
    private Category category;

    @Override
    public String toString () {
        return new StringBuilder("Book{title='")
                .append(title)
                .append("', newPrice=")
                .append(newPrice)
                .append(", oldPrice=")
                .append(oldPrice)
                .append("', currency=")
                .append(currency)
                .append(", year=")
                .append(year)
                .append(", link=")
                .append(link)
                .append(", description=")
                .append(description)
                .append("}").toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17,31)
                .append(title)
                .append(newPrice)
                .append(oldPrice)
                .append(currency)
                .append(year)
                .append(link)
                .append(description)
                .append(printHouse)
                .append(authors)
                .append(category)
                .toHashCode();
    }

    @Override
    public boolean equals(Object bookObj) {
        if (this == bookObj)
            return true;
        if (bookObj == null || getClass() != bookObj.getClass())
            return false;
        Book book = (Book)bookObj;
        return title.equals(book.title) && newPrice == book.newPrice
                && oldPrice == book.oldPrice && currency.equals(book.currency) && year == book.year
                && link.equals(book.link) && description.equals(book.description)
                && printHouse.equals(book.printHouse) && authors.equals(book.authors)
                && category == book.category;
    }
}