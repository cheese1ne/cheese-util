package com.cheese.util.mybatis.plugin.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 资源获取
 * @author sobann
 */
public class BatchPropertiesReader {
    private static Logger LOG = LoggerFactory.getLogger(BatchPropertiesReader.class);

    public static Properties readResources(String fileName) {
        Properties properties = new Properties();
        try {
            InputStream in = BatchPropertiesReader.class.getResourceAsStream(fileName);
            properties.load(in);
        } catch (IOException e) {
            LOG.error("reading properties error", e);
        }
        return properties;
    }
}