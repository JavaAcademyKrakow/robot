package repositories;

import dao.AuthorDAO;
import domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AuthorInput {

    @Autowired
    AuthorDAO authorDAO;

    Author saveAuthor(String name) {
        Author author = authorDAO.findByNameIgnoreCase(name);
        if (author == null) {
            author = Author.builder().name(name).build();
            authorDAO.save(author);
        }
        return author;
    }
}
