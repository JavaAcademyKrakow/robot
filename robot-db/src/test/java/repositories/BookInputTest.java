package repositories;

import dbconfiguration.SpringDBConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


@ContextConfiguration(classes = {SpringDBConfiguration.class})
@Transactional
public class BookInputTest extends AbstractTestNGSpringContextTests {

    @Autowired
    BookInput databaseInput;

    @Test
    public void testSaveIntoDatabase() {
        assertTrue(true);
    }

}