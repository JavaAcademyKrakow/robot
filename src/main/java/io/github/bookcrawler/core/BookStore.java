package io.github.bookcrawler.core;

import io.github.bookcrawler.core.impl.EmpikBooksExtractor;
import io.github.bookcrawler.core.impl.EmpikBookInfoParser;
import io.github.bookcrawler.core.impl.EmpikBookLinksCrawler;
import io.github.bookcrawler.core.impl.JsoupSourceScrapper;

public enum BookStore {

    EMPIK {
        @Override
        public String startUrl() {
            return "http://www.empik.com/ebooki/promocje/";
        }

        @Override
        public String domainUrl() {
            return "http://www.empik.com";
        }

        @Override
        public BookExtractor extractor() {
            return new EmpikBooksExtractor();
        }

        @Override
        public BooksLinkCrawler crawler() {
            return new EmpikBookLinksCrawler(new JsoupSourceScrapper());
        }

        @Override
        public EmpikBookInfoParser parser() {
            return new EmpikBookInfoParser(new JsoupSourceScrapper());
        }
    };

    public abstract String startUrl();

    public abstract String domainUrl();

    public abstract BookExtractor extractor();

    public abstract BooksLinkCrawler crawler();

    public abstract BookInfoParser parser();
}
