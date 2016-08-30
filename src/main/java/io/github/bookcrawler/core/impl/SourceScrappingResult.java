package io.github.bookcrawler.core.impl;

import org.jsoup.nodes.Document;

public class SourceScrappingResult {
    private final Document source;
    private final SourceScrappingStatus status;

    SourceScrappingResult(Document source, SourceScrappingStatus status) {
        this.source = source;
        this.status = status;
    }

    boolean isSuccessful() {
        return status == SourceScrappingStatus.SUCCESS;
    }

    Document getSource() {
        return source;

    }
}
