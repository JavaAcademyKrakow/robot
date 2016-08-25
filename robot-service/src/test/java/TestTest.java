import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestTest {

    @Test
    public void testTest() {
        assertTrue(new TestClass().returnTrue());
    }
}
