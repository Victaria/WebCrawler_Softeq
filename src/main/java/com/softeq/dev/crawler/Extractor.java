package com.softeq.dev.crawler;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class Extractor {
    private HashSet<String> links;
    private HashSet<String> words;
    private Integer[] hits;
    private ResultObject resultObject;

    public Extractor(Set<String> links, Set<String> words) {
        this.links = (HashSet<String>) links;
        this.words = (HashSet<String>) words;
        hits = new Integer[words.size() + 1];

        ResultObject.setWords(words);
    }

    public void countHits() {
        String str = "";

        for (String link : links) {
            StringBuilder sb = new StringBuilder();

            resultObject = new ResultObject(link);

            Document document;

            try {
                document = Jsoup.connect(link).get();
                str = document.body().text().toLowerCase();

                sb.append(link);

                int j = 0;
                int sum = 0;

                for (String word : words) {
                    hits[j] = StringUtils.countMatches(str, word);
                    sum += hits[j];
                    sb.append("  ");
                    sb.append(StringUtils.countMatches(str, word));
                    j++;
                }

                System.out.println(" ****  " + sb);

                hits[words.size()] = sum;

                resultObject.setHits(hits);

                ResultObject.addToResultObjectList(resultObject);

                System.out.println(" ****  " + resultObject.getHits());

            } catch (IOException e) {
                System.out.println("Not valid url ...");
                System.err.println(e.getMessage());
            }
        }

    }

}
