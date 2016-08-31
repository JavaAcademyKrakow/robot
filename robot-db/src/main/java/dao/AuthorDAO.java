package dao;

import domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


/**
 * This class is responsible for managing Authors in database
 */
@Component
public interface AuthorDAO extends CrudRepository<Author, Long> {

}
