package services.dbobjects;

import java.util.List;

/**
 * Created by daniel on 25.08.16.
 */
public interface BookDao {
    void save(Book b);

    List<Book> list();
}
