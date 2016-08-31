package dao;

import domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for managing Books in database
 */

@Component
public interface BookDAO extends CrudRepository<Book, Long> {

}
