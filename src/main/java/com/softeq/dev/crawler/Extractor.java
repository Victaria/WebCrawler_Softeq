package com.softeq.dev.crawler;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class Extractor {
    private HashSet<String> links;
    private List<String> words;
    int[][] hits;

    public Extractor(Set<String> links, Set<String> words) {
        this.links = (HashSet<String>) links;
        this.words = new ArrayList<>(words);
        hits = new int[links.size()][words.size() + 1];
    }

    public void countHits() throws IOException {
        String str = "";
        int i = 0;
        for (String link : links) {
            StringBuilder sb = new StringBuilder();

            Document document;

            try {
                document = Jsoup.connect(link).get();
                str = document.body().text().toLowerCase();

                sb.append(link);

                int j = 0;

                for (String word : words) {
                    hits[i][j] = StringUtils.countMatches(str, word);
                    sb.append("  ");
                    sb.append(StringUtils.countMatches(str, word));
                    j++;
                }

                System.out.println(" ****  " + sb);

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            i++;
        }
        sortAndSeparateResult();
         System.out.println(str);
    }

    public void sortAndSeparateResult() {
        for (int i = 0; i < links.size(); i++) {
            int sum = 0;
            for (int j = 0; j < words.size(); j++) {
                System.out.print(hits[i][j] + "  ");
                sum += hits[i][j];
            }
            hits[i][words.size()] = sum;
            System.out.print(sum);
            System.out.println("");
        }

        System.out.println("============================================");

        Arrays.sort(hits, (x, y) -> y[words.size()]-x[words.size()]);
        for (int[] i : hits)
        {
            for (int j : i)
                System.out.print(String.format("%3d", j));
            System.out.println();
        }
    }

}
