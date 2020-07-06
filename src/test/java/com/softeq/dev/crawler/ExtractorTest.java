package com.softeq.dev.crawler;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ExtractorTest {

    private Extractor extractor;

    @BeforeEach
    void setUp() {
        Set<String> words = new HashSet<>();
        words.add("copyright");
        words.add("пила");
        extractor = new Extractor(new LinksReader()
                .getPageLinks("https://arenda911.by/measuringtools", 0, 0), words);
    }

    @Test
    void countHits() throws IOException {
        extractor.countHits();
        Assert.assertFalse(ResultObject.getResultObjectList().isEmpty());
        Assert.assertTrue(ResultObject.getWords().contains("copyright"));
        Assert.assertTrue(ResultObject.getWords().contains("пила"));
    }
}