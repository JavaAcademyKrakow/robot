package repositories;

import dao.AuthorDAO;
import domain.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link AuthorManager} reads all authors which exists in database and save them in memory
 */
@Repository
@Slf4j
public class AuthorManager {

    private Map<String, Author> authors;
    @Autowired
    private AuthorDAO authorDAO;

    @PostConstruct
    private void init () {
        authors = new HashMap<>();
        authorDAO.findAll().forEach(author -> authors.put(author.getName(), author));
    }

    /**
     * Check if Author with given name exists in a database if not in puts new one
     * @param name  {@link Author} name of author
     * @return {@link Author}
     */
    Author getAuthorForName (String name) {
        if (authors.containsKey(name)) {
            return authors.get(name);
        }
        Author author = Author.builder().name(name).build();
        authors.put(name, author);
        log.info("Author " + author);
        return authorDAO.save(author);
    }
}
