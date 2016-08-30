package io.github.bookcrawler.core;

import io.github.bookcrawler.core.impl.SourceScrappingResult;

@FunctionalInterface
public interface SourceScrapper {

    SourceScrappingResult scrap(String url);
}
