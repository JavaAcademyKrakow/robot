package domain;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests for PrintHouse (equality and hashCode for now).
 */
@Test()
public class PrintHouseTest {

    private final PrintHouse p1 = new PrintHouse();
    private final PrintHouse p2 = new PrintHouse();
    private final PrintHouse p3 = new PrintHouse();

    @BeforeClass
    private void setNames() {
        p1.setName("Print House 1");
        p2.setName("Print House 2");
        p3.setName("Print House 1");
    }

    @DataProvider(name = "hashcode")
    private Object[][] provideHashCodeTestData() {
        // left hashcode (lh) / right hashcode(rh) / expected result of (lh == rh)
        return new Object[][]{
                new Object[]{p1.hashCode(), p2.hashCode(), false},
                new Object[]{p2.hashCode(), p3.hashCode(), false},
                new Object[]{p1.hashCode(), p3.hashCode(), true}
        };
    }

    @DataProvider(name = "equals")
    private Object[][] provideEqualityTestData() {
        // left object (lo) / right object (ro) / expected result of (lo.equals(ro))
        return new Object[][]{
                new Object[]{p1, null, false},
                new Object[]{p1, p2, false},
                new Object[]{p2, p1, false},
                new Object[]{p1, p3, true},
                new Object[]{p3, p1, true},
                new Object[]{p2, p3, false}
        };
    }

    /**
     * Test of hashCode
     * @param leftHash - first object's hashcode
     * @param rightHash - second object's hashcode
     * @param expected - expected result of (left == right)
     */
    @Test(dataProvider = "hashcode")
    public void testPrintHouseHashCode(int leftHash, int rightHash, boolean expected) {
        // given, when
        boolean actual = leftHash == rightHash;
        // then
        assertEquals(actual, expected);
    }

    /**
     * Test of equals() method
     * @param left - first print house object
     * @param right - second print house object
     * @param expected - expected result of left.equals(right)
     */
    @Test(dataProvider = "equals")
    public void testEquality(PrintHouse left, PrintHouse right, boolean expected) {
        // given, when
        boolean actual = left.equals(right);
        // then
        assertEquals(actual, expected);
    }
}