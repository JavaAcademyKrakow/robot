package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static io.github.bookcrawler.core.BookStore.EMPIK;

public class EmpikBookInfoParser implements BookInfoParser {

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        Document source = sourceScrappingResult.getSource();
        return new BookInfoBuilder()
                .title(parseTitle(source))
                .author(parseAuthor(source))
                .description(parseDescription(source))
                .price(parsePrice(source))
                .library(EMPIK.toString())
                .url(EMPIK.domainUrl())
                .build();
    }

    private String parseTitle(Element source) {
        return getTextByAttribute(source, "productMainTitle");
    }

    private String parseAuthor(Element source) {
        return getTextByAttribute(source, "pDAuthorList");
    }

    private String parseDescription(Element source) {
        return getTextByAttribute(source, "contentPacketText longDescription");
    }

    private String parsePrice(Element source) {
        return getTextByAttribute(source, "currentPrice");
    }

    private String getTextByAttribute(Element source, String attribute) {
        return source
                .getElementsByAttributeValue("class", attribute)
                .get(0)
                .text();
    }
}
