package dao;

import domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AuthorDAO extends CrudRepository<Author, Long> {

    Author findByNameIgnoreCase(String name);
}
