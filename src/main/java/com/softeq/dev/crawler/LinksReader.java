package com.softeq.dev.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class LinksReader {
    private static final int MAX_DEPTH = 2;
    private static final int MAX_COUNT = 3;
    private HashSet<String> links;

    public LinksReader() {
        links = new HashSet<>();
    }

    public HashSet<String> getPageLinks(String URL, int depth, int count) {

        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                depth++;
                for (Element page : linksOnPage) {
                    if (count < MAX_COUNT) {
                        getPageLinks(page.attr("abs:href"), depth, count);
                        count++;
                    } else {
                        return links;
                    }
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
            return links;
        } else {
            return links;
        }
    }
}
