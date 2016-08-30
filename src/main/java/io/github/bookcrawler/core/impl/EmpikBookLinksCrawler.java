package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BooksLinkCrawler;
import io.github.bookcrawler.core.SourceScrapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.bookcrawler.core.BookStore.EMPIK;

public class EmpikBookLinksCrawler implements BooksLinkCrawler {

    @Override
    public List<String> crawl(String url, SourceScrapper sourceScrapper) {

        SourceScrappingResult sourceScrappingResult = sourceScrapper.scrap(url);
        if (!sourceScrappingResult.isSuccessful()) {
            return Collections.emptyList();
        }

        return getSectionElements(sourceScrappingResult.getSource()).parallelStream()
                .map(e -> sourceScrapper.scrap(getLink(href(e))))
                .filter(SourceScrappingResult::isSuccessful)
                .map(SourceScrappingResult::getSource)
                .flatMap(e -> getBookElements(e).parallelStream())
                .filter(e -> !href(e).isEmpty())
                .map(e -> getLink(href(e)))
                .collect(Collectors.toList());
    }

    private Elements getBookElements(Document source) {
        return source.getElementsByAttributeValue("class", "productBox-450Pic");
    }

    private Elements getSectionElements(Document source) {
        return source.getElementsByAttributeValue("class", "more_lnk");
    }

    private String getLink(String i) {
        return EMPIK.domainUrl() + i;
    }

    private String href(Element e) {
        return e.attr("href");
    }
}
