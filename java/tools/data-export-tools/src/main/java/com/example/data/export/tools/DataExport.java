package com.example.data.export.tools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.example.data.export.tools.util.Consumer;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * DataExport.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Slf4j
public class DataExport {
    public static void main(String[] args) {
        final String path = "";
        final CsvReader reader = CsvUtil.getReader();
        final Consumer<CsvRow> consumer = new Consumer<>(200);
        final BufferedReader bufferedReader = FileUtil.getReader(path, StandardCharsets.UTF_8);
        final ExecutorService executor = ThreadUtil.newExecutor(10);
        reader.read(bufferedReader, row -> {
            if (!consumer.add(row)) {
                executor.execute(() -> consumer(consumer.consumer()));
                consumer.add(row);
            }
        });
        consumer(consumer.consumer());
        executor.shutdown();
        
    }
    
    private static void consumer(List<CsvRow> consumer) {
        log.info("consume data size is {}", consumer.size());
    }
}
