package logic.parser;

import logic.book.Book;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

/**
 * Test class for EbooksComParser.
 */
@Test()
public class EbooksComParserTest {

    /**
     * Simple test for EbooksComParser - it uses one html file captured from the website
     * @throws IOException - when classloader has problems with finding html file in resources directory.
     */
    @Test
    public void testParser() throws IOException {
        // given
        InputStream is =  ClassLoader.getSystemResourceAsStream("education_example.html");
        String html = IOUtils.toString(is);
        EbooksComParser parser = mock(EbooksComParser.class);

        // when
        when(parser.openDocument()).thenReturn(Jsoup.parse(html));
        when(parser.parse()).thenCallRealMethod();
        Optional<List<Book>> optionalListOfBooks = parser.parse();

        // then
        assertTrue(optionalListOfBooks.get().size() == 8);
    }
}