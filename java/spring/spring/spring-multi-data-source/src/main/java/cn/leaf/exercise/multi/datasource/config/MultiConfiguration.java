package cn.leaf.exercise.multi.datasource.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MultiConfiguration.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Configuration
public class MultiConfiguration {

}
