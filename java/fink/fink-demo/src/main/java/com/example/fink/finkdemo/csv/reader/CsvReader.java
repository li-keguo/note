package com.example.fink.finkdemo.csv.reader;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * CsvReader.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Slf4j
public class CsvReader {
    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        final String path = CsvReader.class.getResource("/").getPath();
        log.info("read csv file path: [{}]", path);
        env.readCsvFile(path + "/test-csv-reader.csv")
                .fieldDelimiter(",")
                .ignoreFirstLine()
                .pojoType(Row.class, "name", "type", "status", "score", "author", "completed", "link")
                .print();
        env.readTextFile(path + "/test-csv-reader.csv")
                .distinct()
                .print();
    }
    
    @Data
    public static class Row {
        private String name;
        private String type;
        private String status;
        private String score;
        private String author;
        private String completed;
        private String link;
    }
}
