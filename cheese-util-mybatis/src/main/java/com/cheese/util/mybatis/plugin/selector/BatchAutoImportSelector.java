package com.cheese.util.mybatis.plugin.selector;

import com.cheese.util.mybatis.plugin.tool.BatchPropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 自定义importSelector，使用batch-auto.properties进行装配
 * 如有需要，可以在任一已装配的类上使用import注入此类
 * @author sobann
 */
public class BatchAutoImportSelector implements ImportSelector {

    private static Logger LOG = LoggerFactory.getLogger(BatchAutoImportSelector.class);

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //读取配置文件中的全类名
        Properties properties = BatchPropertiesReader.readResources("/batch-auto.properties");
        Enumeration<?> enumeration = properties.propertyNames();
        //去重
        Set<String> set = new HashSet<>();
        while (enumeration.hasMoreElements()) {
            String nextElement = (String) enumeration.nextElement();
            set.add(nextElement);
        }
        String[] strings = StringUtils.toStringArray(set);
        LOG.info("prepare to load types:{}", Arrays.toString(strings));
        return strings;
    }
}