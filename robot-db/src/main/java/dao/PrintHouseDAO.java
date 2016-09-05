package dao;

import domain.Category;
import domain.PrintHouse;
import org.springframework.data.repository.CrudRepository;

/**
 * interface for CRUD Operations on {@link PrintHouse}
 */
public interface PrintHouseDAO extends CrudRepository<PrintHouse, Long> {

}
