package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

/**
 * Author representation in Database
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long authorId;

    @Getter
    @Setter
    private String name;

    @ManyToMany(mappedBy = "authors")
    @Getter
    @Setter
    private Collection<Book> books;

}
