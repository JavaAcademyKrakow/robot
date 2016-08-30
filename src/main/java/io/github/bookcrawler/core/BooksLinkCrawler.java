package io.github.bookcrawler.core;

import java.util.List;

@FunctionalInterface
public interface BooksLinkCrawler {

    List<String> crawl(String url, SourceScrapper sourceScrapper);
}
