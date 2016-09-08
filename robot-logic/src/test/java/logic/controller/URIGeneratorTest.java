package logic.controller;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


/**
 * Test class for URIGenerator
 */
@Test()
public class URIGeneratorTest {
    @DataProvider(name = "URIdataSingle")
    private Object[][] provideURIData() {
        return new Object[][]{
                new Object[]{"http://www.something.com/books/ebook?list=###", 4, "http://www.something.com/books/ebook?list=4"},
                new Object[]{"http://www.something.com/books/ebook?list=###&sortBy=asc", 0, "http://www.something.com/books/ebook?list=0&sortBy=asc"}
        };
    }


    @DataProvider(name = "URIsequence")
    private Object[][] provideURISequence() {
        final URIGenerator generator = new URIGenerator("http://www.something.com/books/ebook?list=###&sortBy=asc", 0);

        return new Object[][]{
                new Object[]{generator.generateNextFullURI(), "http://www.something.com/books/ebook?list=0&sortBy=asc"},
                new Object[]{generator.generateNextFullURI(), "http://www.something.com/books/ebook?list=1&sortBy=asc"},
                new Object[]{generator.generateNextFullURI(), "http://www.something.com/books/ebook?list=2&sortBy=asc"},
                new Object[]{generator.generateNextFullURI(), "http://www.something.com/books/ebook?list=3&sortBy=asc"}
        };
    }

    @DataProvider(name = "URIsequenceSingleArg")
    private Object[][] provideSequenceWithSingleArgs() {
        final URIGenerator generator = new URIGenerator("http://www.something.com/logic.book=###");

        return new Object[][]{
                new Object[]{generator.generateNextFullURI(), "http://www.something.com/logic.book=1"},
                new Object[]{generator.generateNextFullURI(), "http://www.something.com/logic.book=2"},
                new Object[]{generator.generateNextFullURI(), "http://www.something.com/logic.book=3"}
        };
    }


    /**
     * Simple test of generating sequences by URIGenerator.
     *
     * @param base          - base string
     * @param startingIndex - starting index (to replace ### pattern)
     * @param expected      - expected result
     */
    @Test(dataProvider = "URIdataSingle")
    public void testGeneratingFirstURI(String base, int startingIndex, String expected) {
        // given
        URIGenerator generator = new URIGenerator(base, startingIndex);
        // when
        String result = generator.generateNextFullURI();
        //then
        assertEquals(result, expected);
    }


    /**
     * Simple test of generating sequences staring with default (1) starting index
     *
     * @param actual   - generated string (link)
     * @param expected - expected link
     */
    @Test(dataProvider = "URIsequence")
    public void testSequence(String actual, String expected) {
        //then
        assertEquals(actual, expected);
    }

    /**
     * Test that checks the incremental behavior of URIGenerator.
     *
     * @param actual   - generated link
     * @param expected - expected link
     */
    @Test(dataProvider = "URIsequenceSingleArg")
    public void testGeneratingSequenceWithSingleArg(String actual, String expected) {
        // then
        assertEquals(actual, expected);
    }

    /**
     * Test of HashCode calculated by HashCodeBuilder.
     */
    @Test
    public void hashCodeTest() {
        // given
        URIGenerator u1 = new URIGenerator("aa");
        URIGenerator u2 = new URIGenerator("bb");

        // when
        int u1Hash = u1.hashCode();
        int u2Hash = u2.hashCode();

        // then
        assertTrue(u1Hash != u2Hash);
    }


    /**
     * Equality test.
     */
    @Test
    public void equalsTest() {
        // given
        URIGenerator u1 = new URIGenerator("aa");
        URIGenerator u2 = new URIGenerator("bb");
        URIGenerator u3 = new URIGenerator("aa");

        // when
        u3.generateNextFullURI();

        // then
        assertTrue(u1.equals(u1));
        assertTrue(u2.equals(u2));
        assertFalse(u1.equals(u2));
        assertFalse(u1.equals(null));
        assertFalse(u1.equals(new Integer(4)));
        assertFalse(u1.equals(u3));
        assertFalse(u2.equals(u1.generateNextFullURI()));
    }
}
