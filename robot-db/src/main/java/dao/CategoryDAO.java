package dao;

import domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
  * This class is responsible for managing Categorise in database
 */

@Component
public interface CategoryDAO extends CrudRepository<Category, Long> {

}
