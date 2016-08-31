package domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Category Representation in Database
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

}
