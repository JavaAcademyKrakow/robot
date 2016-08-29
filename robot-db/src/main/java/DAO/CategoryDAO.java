package DAO;

import domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryDAO extends CrudRepository<Category, Long> {

}
