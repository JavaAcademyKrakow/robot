package domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Representation of an {@link CategoryName} a Database.
 * Category is mapped {@code @OnoToMany} to {@link Book}
 * Do not use CascadeType or fetchType.Eager due to performance issue
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long categoryId;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @OneToMany(mappedBy = "category")
    @Getter
    @Setter
    private Collection<Book> books;

    @Override
    public String toString () {
        return new StringBuilder("Category{name=")
                .append(name)
                .append("}").toString();
    }
}
