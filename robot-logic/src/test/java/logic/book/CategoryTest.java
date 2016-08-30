package logic.book;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static logic.book.Category.*;
import static org.testng.Assert.assertEquals;

/**
 * Simple test class for Category enum.
 */
@Test()
public class CategoryTest {
    @DataProvider(name = "categoryDescription")
    private Object[][] testDescriptions() {
        return new Object[][]{
                new Object[]{EDUCATION_AND_SCIENCE, "Education & science"},
                new Object[]{TRAVEL, "Travelling"},
                new Object[]{LIFESTYLE, "Lifestyle & cooking"},
                new Object[]{SEX, "Sex"},
                new Object[]{MEDICINE, "Medicine"},
                new Object[]{ADVENTURE, "Adventure & hobby"}
        };
    }

    /**
     * Test of descriptions for all categories available in enum. Yeah, this is test of java feature, bu JaCoCo wants
     * this (missing coverage).
     * @param category - category enum
     * @param description - expected description of the category
     */
    @Test(dataProvider = "categoryDescription")
    public void testCategoryDescriptions(Category category, String description) {
        // given, when
        String actualDescription = category.getDescription();
        // then
        assertEquals(actualDescription, description);
    }
}
