package com.equbik.framework.csv_model;

import com.equbik.framework.model.Elements;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class MapCSVToElement {

    /*
     * MapCSVToElement class used for mapping csv model to Elements class
     */

    private final String csvFile;
    private final String[] HEADERS = {"id", "flow", "page", "name", "xpath", "typeId", "value", "relatedElement"};

    public MapCSVToElement(String csvFile) {
        this.csvFile = csvFile;
    }

    public List<Elements> csvToElementsList() {
        List<Elements> elementsList = new ArrayList<>();
        try (Reader reader = new FileReader(csvFile, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, getFormat())) {
            for (CSVRecord csvRecord : csvParser) {
                Integer id = Integer.parseInt(csvRecord.get(0));
                String flow = csvRecord.get(1);
                String page = csvRecord.get(2);
                String name = csvRecord.get(3);
                String xpath = csvRecord.get(4);
                Integer typeId = Integer.parseInt(csvRecord.get(5));
                String value = csvRecord.get(6);
                String relatedElement = csvRecord.get(7);

                Elements element = new Elements(id, flow, page, name, xpath, typeId, value, relatedElement);
                elementsList.add(element);
            }
            return elementsList;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private CSVFormat getFormat() {
        return CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();
    }

}
