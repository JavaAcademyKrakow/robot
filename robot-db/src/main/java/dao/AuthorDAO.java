package dao;

import domain.Author;
import domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


/**
 * interface for CRUD Operations on {@link Author}
 */
@Component
public interface AuthorDAO extends CrudRepository<Author, Long> {

}
