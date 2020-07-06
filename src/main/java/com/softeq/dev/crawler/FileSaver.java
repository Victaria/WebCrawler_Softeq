package com.softeq.dev.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class FileSaver {

    public void saveToCsv(String name, List<ResultObject> resultList) throws IOException {
        FileWriter csvWriter = new FileWriter(name);

        csvWriter.append(ResultObject.getWords().toString());
        csvWriter.append("\n");

        for (ResultObject resObj : resultList) {
            csvWriter.append(resObj.getLink());
            csvWriter.append("  " + resObj.getHits());
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    public void filterTopAndPrint() throws IOException {
        List<ResultObject> resList = ResultObject.getResultObjectList();

        resList.sort(new Comparator<ResultObject>() {
            @Override
            public int compare(ResultObject resObj1, ResultObject resObj2) {
                return -(resObj1.getHitsResult() - resObj2.getHitsResult());
            }
        });

        if (resList.size() > 10) {
            resList = resList.subList(0, 10);
        }

        for (ResultObject resultObject : resList) {
            System.out.println(resultObject.getLink() + "  " + resultObject.getHits());
        }

        saveToCsv("topWords.csv", resList);
    }
}
