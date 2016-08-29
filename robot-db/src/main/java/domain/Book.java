package domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


/**
 * First idea of entity representing books.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue
    @Setter @Getter
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
    @Column(name="old_price")
    private float oldPrice;


    @Getter
    @Setter
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
    private String printHouse;


    @ManyToMany
    @Setter @Getter
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    @Setter @Getter
    private Category category;

}
