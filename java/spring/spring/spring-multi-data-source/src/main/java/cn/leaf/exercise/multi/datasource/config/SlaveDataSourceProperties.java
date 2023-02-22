package cn.leaf.exercise.multi.datasource.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 多相关从库配置
 *
 * @author likeguo
 */
@Data
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "multi-data-source")
public class SlaveDataSourceProperties {
    
    private final DataSourceProperties master;
    
    private Map<String, DataSourceProperties> slaves;
}
