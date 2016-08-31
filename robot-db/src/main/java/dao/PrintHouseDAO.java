package dao;

import domain.PrintHouse;
import org.springframework.data.repository.CrudRepository;

/**
 * This class is responsible for manging PrintHouses in database
 */
public interface PrintHouseDAO extends CrudRepository<PrintHouse, Long> {

}
