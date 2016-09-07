package logic.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import repositories.ParsedBook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Test class for EbooksComParser.
 */
@Test()
public class EbooksComParserTest {

    /**
     * This test covers the case in which parser tries to parse empty web page.
     * In this case, empty Optional should be returned.
     *
     * @throws IOException - when classloader has problems with finding html file in resources directory.
     */
    @Test
    public void testEmptyDocument() throws IOException {
        // given
        final String emptyWebPage = "<html><head><title>Empty doc</title></head><body>There are no books in here!</body></html>";
        final EbooksComParser parser = mock(EbooksComParser.class);

        // when
        when(parser.openDocument()).thenReturn(Jsoup.parse(emptyWebPage));
        when(parser.parse()).thenCallRealMethod();
        Optional<List<ParsedBook>> resultList = parser.parse();

        // then
        assertFalse(resultList.isPresent());
    }

    /**
     * In this case the document does not have any free books or books on discount.
     * Expected result of parsing is an empty list.
     *
     * @throws IOException - when classloader has problems with finding html file in resources directory.
     */
    @Test
    public void testDocumentWithoutBooksOnDiscount() throws IOException {
        // given
        InputStream is = ClassLoader.getSystemResourceAsStream("ebooks_com/no_discount.html");
        EbooksComParser parser = mock(EbooksComParser.class);
        Document doc = Jsoup.parse(is, null, "http://www.ebooks.com/subjects/education/");

        // when
        when(parser.openDocument()).thenReturn(doc);
        when(parser.parse()).thenCallRealMethod();
        Optional<List<ParsedBook>> resultList = parser.parse();
        List<ParsedBook> unpackedList;

        if (resultList.isPresent())
            unpackedList = resultList.get();
        else
            unpackedList = null;

        // then
        assertTrue(unpackedList != null && unpackedList.isEmpty());
    }


    /**
     * This method performs scenario in which some books on the discount are found. It uses html retrieved
     * from one of the web pages from ebooks.com. To simplify the test, only the number of books found are checked.
     *
     * @throws IOException - when classloader has problems with finding html file in resources directory.
     */
    @Test
    public void testParser() throws IOException {
        // given
        InputStream is = ClassLoader.getSystemResourceAsStream("ebooks_com/with_discount.html");
        EbooksComParser parser = mock(EbooksComParser.class);
        Document doc = Jsoup.parse(is, null, "http://www.ebooks.com/subjects/education/");

        // when
        when(parser.openDocument()).thenReturn(doc);
        when(parser.parse()).thenCallRealMethod();
        Optional<List<ParsedBook>> optionalListOfBooks = parser.parse();
        List<ParsedBook> unpackedList;

        if (optionalListOfBooks.isPresent())
            unpackedList = optionalListOfBooks.get();
        else
            unpackedList = null;

        // then
        assertTrue(unpackedList != null && unpackedList.size() == 8);
    }
}