package dao;


import dbconfiguration.SpringDBConfiguration;
import domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = {SpringDBConfiguration.class})
@Transactional
public class AuthorDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    AuthorDAO authorDAO;

    @Test
    public void testFindByName() {
        // Given
        Author author = Author.builder().name("test").build();
        authorDAO.save(author);

        // When

        Author fromDatabase = authorDAO.findByNameIgnoreCase("test");

        // Then
        assertEquals(fromDatabase.getName(), author.getName());


    }
}