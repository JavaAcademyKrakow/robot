package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class DiscountFetchingService {
    public Collection<BookInfo> fetch(Collection<BookStore> bookStores) {

        return bookStores.parallelStream()
                .flatMap(b -> b.extractor().extract().parallelStream())
                .collect(Collectors.toList());
    }
}
