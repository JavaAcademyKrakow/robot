package dao;

import domain.Category;
import domain.CategoryName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryDAO extends CrudRepository<Category, Long> {

    Category findByName(CategoryName name);
}
