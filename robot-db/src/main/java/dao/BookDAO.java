package dao;

import domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * interface for CRUD Operations on {@link Book}
 */
@Component
public interface BookDAO extends CrudRepository<Book, Long> {

    /**
     * Selects from a database all information about the book
     * for given pageable parameter
     * example of usage:
     * @<code> findAll(new PageRequest(1,20).getContent() </code>
     * it will return list of first 20 books
     * @param pageable {@link Pageable}
     * @return {@link Page}
     */
    Page<Book> findAll(Pageable pageable);

}
