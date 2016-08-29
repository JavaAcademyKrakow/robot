package DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryDAO extends CrudRepository<CategoryDAO, Long> {

}
