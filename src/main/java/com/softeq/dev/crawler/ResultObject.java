package com.softeq.dev.crawler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ResultObject {

    private static List<ResultObject> resultObjectList = new ArrayList<>();
    private String link;
    private static List<String> words;
    private String hits;
    private Integer hitsResult;

    public Integer getHitsResult() {
        return hitsResult;
    }

    public void setHitsResult(int num) {
        this.hitsResult = num;
    }

    public ResultObject(String link) {
        this.link = link;
    }

    public static void setWords(Set<String> wordList) {
        words = new ArrayList<>(wordList);
    }

    public static void addToResultObjectList(ResultObject resultObject) {
        resultObjectList.add(resultObject);
    }

    public static List<ResultObject> getResultObjectList() {
        return resultObjectList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static List<String> getWords() {
        return words;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(Integer[] hits) {
        this.hits = Arrays.toString(hits).replace("[", "").replace("]", "");
        setHitsResult(hits[hits.length - 1]);
    }

}
