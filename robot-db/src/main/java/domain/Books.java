package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


/**
 * First idea of entity representing books.
 */
@Entity
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue
    @Setter @Getter
    private long bookID;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String newPrice;

    @Getter
    @Setter
    private String oldPrice;


    @Getter
    @Setter
    private String description;


    @ManyToMany
    @Setter @Getter
    private Collection<Author> authors;

    @ManyToOne
    @Setter @Getter
    private Category category;
}
