package dao;

import domain.CategoryName;
import domain.PrintHouse;
import org.springframework.data.repository.CrudRepository;


public interface PrintHouseDAO extends CrudRepository<PrintHouse, Long> {

    PrintHouse findByNameIgnoreCase(String name);
}
