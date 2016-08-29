package DAO;


import dbconfiguration.SpringDBConfiguration;
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


    public void BooksInsertTest() {

    }


}