package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.SourceScrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupSourceScrapper implements SourceScrapper {
    // TODO: need to handle exception in better way, right now null value passed to SourceScrappingResult

    @Override
    public SourceScrappingResult scrap(String url) {
        try {
            final Document document = Jsoup.connect(url).timeout(0).get();
            return new SourceScrappingResult(document, SourceScrappingStatus.SUCCESS);
        } catch (IOException e) {
            return new SourceScrappingResult(null, SourceScrappingStatus.FAILURE);
        }
    }
}
