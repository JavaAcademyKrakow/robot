package domain;

import lombok.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

/**
 * {@link Author} is a representation of an author in a database.
 * Field name brakes rule of normalization because we store name and surname
 * The instance of book can be created by a builder.
 * Do not use CascadeType or fetchType.Eager due to performance issue.
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

    @Override
    public String toString () {
        return new StringBuilder("Author{authorId=")
                .append(authorId)
                .append(", name='")
                .append(name)
                .append("'}")
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(name).append(books)
                .toHashCode();
    }

    @Override
    public boolean equals(Object authorObj) {
        if (this == authorObj)
            return true;
        if (authorObj == null || getClass() != authorObj.getClass())
            return false;
        Author author = (Author)authorObj;
        return name.equals(author.name);
    }
}