package com.softeq.dev.crawler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static Set<String> set;

    public static void main(String[] args) throws IOException {
        readAllFromConsole();
        //new ResultObject(new LinksReader().getPageLinks("https://farhatsweets.com/category/cakes/", 0, 0), set);
        new Extractor(new LinksReader().getPageLinks("https://farhatsweets.com/category/cakes/", 0, 0), set).countHits();
    }

    public static void readAllFromConsole() {
        System.out.println("Enter words: ");
        Scanner sc = new Scanner(System.in);
        set = Arrays.stream(sc.nextLine().split(",")).sorted().map(String::trim).collect(Collectors.toSet());
        sc.close();
        System.out.println(set);
    }
}
