package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.entities.BookInfo;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.bookcrawler.core.BookStore.EMPIK;

public class EmpikBooksExtractor implements BookExtractor {

    private SourceScrapper sourceScrapper;

    public EmpikBooksExtractor(SourceScrapper sourceScrapper) {
        this.sourceScrapper = sourceScrapper;
    }

    @Override
    public List<BookInfo> extract() {
        return EMPIK.crawler().crawl(EMPIK.startUrl(), sourceScrapper).parallelStream()
                .map(url -> sourceScrapper.scrap(url))
                .filter(SourceScrappingResult::isSuccessful)
                .map(sourceScrappingResult -> EMPIK.parser().parse(sourceScrappingResult))
                .collect(Collectors.toList());
    }
}

