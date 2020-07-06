package com.softeq.dev.crawler;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class LinksReaderTest {

    @Test
    void countPageLinks() {
        Assert.assertNotEquals(new LinksReader()
                .getPageLinks("https://arenda911.by/measuringtools", 0, 0).size(), 4);
        Assert.assertNotEquals(new LinksReader()
                .getPageLinks("https://easyjava.ru/data/hibernate/hello-hibernate-bez-jpa/", 0, 0).size(), 4);
    }
}