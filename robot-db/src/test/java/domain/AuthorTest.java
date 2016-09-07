package domain;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test()
public class AuthorTest {
    private final Author a1 = new Author();
    private final Author a2 = new Author();
    private final Author a3 = new Author();

    @BeforeClass
    private void prepareAuthors() {
        a1.setName("Author First");
        a2.setName("Author Second");
        a3.setName("Author First");
    }

    @DataProvider(name = "hashcode")
    private Object[][] provideHashCodeTestData() {
        // left hashcode (lh) / right hashcode(rh) / expected result of (lh == rh)
        return new Object[][]{
                new Object[]{a1.hashCode(), a2.hashCode(), false},
                new Object[]{a2.hashCode(), a3.hashCode(), false},
                new Object[]{a1.hashCode(), a3.hashCode(), true}
        };
    }

    @DataProvider(name = "equals")
    private Object[][] provideEqualityTestData() {
        // left object (lo) / right object (ro) / expected result of (lo.equals(ro))
        return new Object[][]{
                new Object[]{a1, null, false},
                new Object[]{a1, a2, false},
                new Object[]{a2, a1, false},
                new Object[]{a1, a3, true},
                new Object[]{a3, a1, true},
                new Object[]{a2, a3, false}
        };
    }

    @Test(dataProvider = "hashcode")
    public void testHashCode(int leftHash, int rightHash, boolean expected) {
        // given, when
        boolean actual = leftHash == rightHash;
        // then
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "equals")
    public void testEquality(Author left, Author right, boolean expected) {
        // given, when
        boolean actual = left.equals(right);
        // then
        assertEquals(actual, expected);
    }
}
