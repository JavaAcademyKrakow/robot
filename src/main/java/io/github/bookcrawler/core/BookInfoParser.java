package io.github.bookcrawler.core;

import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;

@FunctionalInterface
public interface BookInfoParser {

    BookInfo parse(SourceScrappingResult sourceScrappingResult);
}
