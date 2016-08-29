package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
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
