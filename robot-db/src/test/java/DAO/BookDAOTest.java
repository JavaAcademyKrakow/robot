package DAO;

import DBConfiguration.SpringDBConfiguration;
import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = {SpringDBConfiguration.class})
@Transactional
public class BookDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    BookDAO bookDAO;


    public void BooksInsertTest() {

    }


}