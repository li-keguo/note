package com.example.data.export.tools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.example.data.export.tools.util.Consumer;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        try (HikariDataSource dataSource = getDataSource()) {
            reader.read(bufferedReader, row -> {
                if (!consumer.add(row)) {
                    executor.execute(() -> consumer(consumer.consumer(), dataSource));
                    consumer.add(row);
                }
            });
            consumer(consumer.consumer());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        executor.shutdown();
        
    }
    
    private static HikariDataSource getDataSource() {
        // config datasource
        return new HikariDataSource();
    }
    
    private static void consumer(final List<CsvRow> consumer, final DataSource dataSource) {
        log.info("consume data size is {}", consumer.size());
        final long start = System.currentTimeMillis();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement("sql")) {
            for (CsvRow row : consumer) {
                for (int i = 0; i < row.size(); i++) {
                    statement.setObject(i + 1, row.get(i));
                }
                statement.addBatch();
            }
            statement.executeBatch();
            log.info("insert data count : {} Time consuming : {}", consumer.size(), System.currentTimeMillis() - start);
        } catch (SQLException e) {
            log.error("exec sql error ", e);
        }
    }
    
    private static void consumer(List<CsvRow> consumer) {
        log.info("consume data size is {}", consumer.size());
    }
}
