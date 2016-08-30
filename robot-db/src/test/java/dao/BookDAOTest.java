package dao;


import dbconfiguration.SpringDBConfiguration;
import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;



@ContextConfiguration(classes = {SpringDBConfiguration.class})
@Transactional
public class BookDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    BookDAO bookDAO;

    @Test
    public void BooksInsertTest() {
        Book book = Book.builder().currency("te").newPrice(23).link("test").build();
        bookDAO.save(book);

        bookDAO.findOne(1L);

    }


}