package dao;

import domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for managing Books in database
 */

@Component
public interface BookDAO extends CrudRepository<Book, Long> {

    /**
     * Method return selected amount of books for given page
     * @param pageable pagination information
     * @return List of books
     */
    Page<Book> FindAll(Pageable pageable);
}
