package dao;

import domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * interface for CRUD Operations on {@link Category}
 */
@Component
public interface CategoryDAO extends CrudRepository<Category, Long> {

}
