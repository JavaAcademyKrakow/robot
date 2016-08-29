package DAO;

import domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface BookDAO extends CrudRepository<Book, Long> {

}
