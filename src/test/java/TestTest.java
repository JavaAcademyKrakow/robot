import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class TestTest {

    @Test
    public void testTest() {
        assertTrue(new TestClass().returnTrue());
    }
}
