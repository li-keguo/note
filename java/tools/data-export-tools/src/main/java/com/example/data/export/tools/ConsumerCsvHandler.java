package com.example.data.export.tools;

import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvRowHandler;
import com.example.data.export.tools.util.Consumer;

import java.util.List;

/**
 * ConsumerCsvHandler.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class ConsumerCsvHandler implements CsvRowHandler {
    
    private final Consumer<CsvRow> consumer;
    
    public ConsumerCsvHandler(Consumer<CsvRow> consumer) {
        this.consumer = consumer;
    }
    
    @Override
    public void handle(CsvRow row) {
        if (!consumer.add(row)) {
            consume(consumer.consumer());
            consumer.add(row);
        }
    }
    
    private void consume(List<CsvRow> consumer) {
    
    }
}
