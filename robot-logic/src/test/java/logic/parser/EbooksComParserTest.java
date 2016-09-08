package logic.parser;

import domain.CategoryName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import repositories.ParsedBook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static domain.CategoryName.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Test class for EbooksComParser.
 */
@Test()
public class EbooksComParserTest {

    // Test helper method - to avoid code duplication
    private static Optional<List<ParsedBook>> retrieveListOfBooks(String resourcePath, String baseURI) throws IOException {
        // given
        InputStream is = ClassLoader.getSystemResourceAsStream(resourcePath);
        EbooksComParser parser = mock(EbooksComParser.class);
        Document doc = Jsoup.parse(is, null, baseURI);
        is.close();

        // when
        when(parser.openDocument()).thenReturn(doc);
        when(parser.parse()).thenCallRealMethod();

        return parser.parse();
    }

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
        // given, when
        Optional<List<ParsedBook>> resultList = retrieveListOfBooks("ebooks_com/no_discount.html", "http://www.ebooks.com/subjects/education/");

        // then
        assertTrue(resultList.isPresent() && resultList.get().isEmpty());
    }


    /**
     * This method performs scenario in which some books on the discount are found. It uses html retrieved
     * from one of the web pages from ebooks.com. To simplify the test, only the number of books found are checked.
     *
     * @throws IOException - when classloader has problems with finding html file in resources directory.
     */
    @Test
    public void testParserWithBookDiscount() throws IOException {
        // given, when
        Optional<List<ParsedBook>> resultList = retrieveListOfBooks("ebooks_com/with_discount.html", "http://www.ebooks.com/subjects/education/");

        // then
        assertTrue(resultList.isPresent() && resultList.get().size() == 8);
    }


    /**
     * This test checks if the particular ParsedBook (value object) is on the list returned by parse() method.
     *
     * @throws IOException - when classloader has problems with finding html file in resources directory.
     */
    @Test
    public void checkIfOneParticularBookIsOnTheReturnList() throws IOException {
        // given
        final String description = "Engaging the Disengaged addresses strategies and models of immersive teaching and learning" +
                " that lead to successful schooling outcomes. The new Australian Curriculum emphasises the importance of" +
                " improved educational participation. This book will equip pre-service teachers with the tools and strategies" +
                " they need to successfully implement these priorities. Drawing together a diverse range of experts, this book" +
                " offers innovative ways of thinking about student engagement. Addressing education across early primary, middle" +
                " and secondary school levels, it explores how differences in culture, sexuality and wealth can alienate students," +
                " and examines challenges faced by schools in rural, remote and high-poverty settings. It also offers new ideas for" +
                " engaging students in subjects such as mathematics, physical education and the arts. Contemporary, real-life case" +
                " studies help connect theory to practice. Each chapter also includes learning objectives, further reading suggestions" +
                " and a reflective closure, as well as a set of strategies for invigorating disadvantaged students.";

        final String link = "http://www.ebooks.com/1139408/engaging-the-disengaged/mckenna-tarquam-cacciattolo-marcelle-vicars-mark/";

        CategoryName category = EDUCATION_AND_SCIENCE;

        ParsedBook expectedBook = ParsedBook
                .builder()
                .authors(Arrays.asList("Tarquam McKenna", "Marcelle Cacciattolo", "Mark Vicars"))
                .category(category)
                .currency("€")
                .description(description)
                .link(link)
                .newPrice(67.58f)
                .oldPrice(82.41f)
                .printHouse("Cambridge University Press")
                .title("Engaging the Disengaged")
                .year((short)2013)
                .build();

        InputStream is = ClassLoader.getSystemResourceAsStream("ebooks_com/with_discount.html");
        Document doc = Jsoup.parse(is, null, "http://www.ebooks.com/subjects/education/");
        is.close();

        EbooksComParser parser = mock(EbooksComParser.class);

        // when
        when(parser.openDocument()).thenReturn(doc);
        when(parser.parse()).thenCallRealMethod();
        when(parser.setCategory(isA(CategoryName.class))).thenCallRealMethod();
        when(parser.setLink(isA(String.class))).thenCallRealMethod();

        parser.setLink("http://www.ebooks.com/subjects/education/?sortBy=&sortOrder=&RestrictBy=&countryCode=pl&page=18");
        parser.setCategory(EDUCATION_AND_SCIENCE);

        Optional<List<ParsedBook>> resultList = parser.parse();

        // then
        assertTrue(resultList.isPresent() && resultList.get().contains(expectedBook));
    }
}