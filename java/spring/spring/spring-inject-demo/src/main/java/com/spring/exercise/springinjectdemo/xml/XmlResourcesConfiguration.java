package com.spring.exercise.springinjectdemo.xml;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * xml 资源配置
 */
@ImportResource(locations = {"/MATE-INFO/xml/xml-assembled-config.xml"})
@Configuration
public class XmlResourcesConfiguration {
}
