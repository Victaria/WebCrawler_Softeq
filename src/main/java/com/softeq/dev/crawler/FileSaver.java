package com.softeq.dev.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileSaver {

    public void saveToCsv(List<String> list) throws IOException {
        FileWriter csvWriter = new FileWriter("new.csv");

        for (String data : list) {
            csvWriter.append(data);
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
