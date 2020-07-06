package com.softeq.dev.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.UnknownHostException;
import java.util.HashSet;

public class LinksReader {
    private static final int MAX_DEPTH = 2;
    private static final int MAX_COUNT = 15;
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
                    if (count < MAX_COUNT - 1) {
                        getPageLinks(page.attr("abs:href"), depth, count);
                    } else {
                        return links;
                    }
                    count++;
                }
            } catch (UnknownHostException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return links;
        } else {
            return links;
        }
    }

}
