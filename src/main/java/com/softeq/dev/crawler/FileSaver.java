package com.softeq.dev.crawler;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class FileSaver {

    public void saveToCsv(String name, List<ResultObject> resultList) throws IOException {
        FileWriter csvWriter = new FileWriter(name);

        CSVWriter writer = new CSVWriter(csvWriter, ',');

        String[] header = new String[ ResultObject.getWords().size() + 2];
        int i = 1;
        header [0] = "Url";
        for (String str : ResultObject.getWords()) {
            header[i] = str;
            i++;
        }
        header[header.length - 1] = "sum of hits";
        writer.writeNext(header);

        for (ResultObject resObj : resultList) {
            String[] result = new String[resObj.getHits().split(",").length + 1];
            result[0] = resObj.getLink();

            String[] hits = resObj.getHits().split(",");

            System.arraycopy(hits, 0, result, 1, result.length - 1);

            writer.writeNext(result);
        }

        writer.close();
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
