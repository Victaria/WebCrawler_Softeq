package com.softeq.dev.crawler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static Set<String> wordsSet;

    public static void main(String[] args) throws IOException {
        readAllFromConsole();
        new Extractor(new LinksReader().getPageLinks("https://arenda911.by/measuringtools", 0, 0), wordsSet).countHits();

        new FileSaver().saveToCsv("new.csv", ResultObject.getResultObjectList());
        new FileSaver().filterTopAndPrint();
    }

    /**
     * Reading words from console and split by ','
     * */
    public static void readAllFromConsole() {
        System.out.println("Enter words: ");
        Scanner sc = new Scanner(System.in);
        wordsSet = Arrays.stream(sc.nextLine().split(",")).sorted().map(String::trim).collect(Collectors.toSet());
        sc.close();
        System.out.println(wordsSet);
    }
}
