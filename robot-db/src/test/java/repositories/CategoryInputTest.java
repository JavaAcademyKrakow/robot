package repositories;


import dbconfiguration.SpringDBConfiguration;
import domain.CategoryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {SpringDBConfiguration.class})
@Transactional
public class CategoryInputTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CategoryInput categoryInput;

    @Test
    public void testSaveCategory() {
        // Given
        categoryInput.saveCategory(CategoryName.ADVENTURE);

    }
}