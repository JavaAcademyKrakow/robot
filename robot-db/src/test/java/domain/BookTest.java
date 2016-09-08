package domain;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static java.util.Arrays.*;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

/**
 * Tests for Book class.
 */
@Test()
public class BookTest {

    private final Book b1 = new Book();
    private final Book b2 = new Book();
    private final Book b3 = new Book();

    @BeforeClass
    private void prepareBooks() {
        Author a1 = mock(Author.class);
        Author a2 = mock(Author.class);
        Author a3 = mock(Author.class);

        b1.setAuthors(asList(a1, a2));
        b2.setAuthors(asList(a3));
        b3.setAuthors(asList(a1, a2));

        b1.setBookID(1);
        b2.setBookID(2);
        b3.setBookID(3);

        Category c1 = mock(Category.class);
        Category c2 = mock(Category.class);

        b1.setCategory(c1);
        b2.setCategory(c2);
        b3.setCategory(c1);

        b1.setCurrency("$");
        b2.setCurrency("$");
        b3.setCurrency("$");

        b1.setDescription("Interesting book");
        b2.setDescription("Another book");
        b3.setDescription("Interesting book");

        b1.setLink("www.link1.com");
        b2.setLink("www.link1.com");
        b3.setLink("www.link1.com");

        b1.setNewPrice(22.22f);
        b2.setNewPrice(12.32f);
        b3.setNewPrice(22.22f);

        b1.setOldPrice(34.20f);
        b2.setOldPrice(34.20f);
        b3.setOldPrice(34.20f);

        PrintHouse p = mock(PrintHouse.class);

        b1.setPrintHouse(p);
        b2.setPrintHouse(p);
        b3.setPrintHouse(p);

        b1.setYear((short)2003);
        b2.setYear((short)2001);
        b3.setYear((short)2003);

        b1.setTitle("TITLE 1");
        b2.setTitle("TITLE 2");
        b3.setTitle("TITLE 1");
    }

    @DataProvider(name = "hashcode")
    private Object[][] provideHashCodeTestData() {
        // left hashcode (lh) / right hashcode(rh) / expected result of (lh == rh)
        return new Object[][]{
                new Object[]{b1.hashCode(), b2.hashCode(), false},
                new Object[]{b2.hashCode(), b3.hashCode(), false},
                new Object[]{b1.hashCode(), b3.hashCode(), true}
        };
    }

    @DataProvider(name = "equals")
    private Object[][] provideEqualityTestData() {
        // left object (lo) / right object (ro) / expected result of (lo.equals(ro))
        return new Object[][]{
                new Object[]{b1, null, false},
                new Object[]{b1, b2, false},
                new Object[]{b2, b1, false},
                new Object[]{b1, b3, true},
                new Object[]{b3, b1, true},
                new Object[]{b2, b3, false}
        };
    }

    /**
     * Test of hashCode
     * @param leftHash - first object's hashcode
     * @param rightHash - second object's hashcode
     * @param expected - expected result of (left == right)
     */
    @Test(dataProvider = "hashcode")
    public void testHashCode(int leftHash, int rightHash, boolean expected) {
        // given, when
        boolean actual = leftHash == rightHash;
        // then
        assertEquals(actual, expected);
    }

    /**
     * Test of equals() method
     * @param left - first book object
     * @param right - second book object
     * @param expected - expected result of left.equals(right)
     */
    @Test(dataProvider = "equals")
    public void testEquality(Book left, Book right, boolean expected) {
        // given, when
        boolean actual = left.equals(right);
        // then
        assertEquals(actual, expected);
    }
}
