package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;

import java.util.List;

@FunctionalInterface
public interface BookExtractor {

    List<BookInfo> extract();
}
