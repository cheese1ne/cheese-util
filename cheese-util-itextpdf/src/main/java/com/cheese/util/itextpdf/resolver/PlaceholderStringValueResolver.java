package com.cheese.util.itextpdf.resolver;


import com.cheese.core.tool.util.Func;

import java.util.Map;

/**
 * 字符串占位符解析处理器
 *
 * @author sobann
 */
public class PlaceholderStringValueResolver {

    /**
     * Default placeholder prefix
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    /**
     * 字符串占位符处理
     *
     * @param value
     * @param properties
     * @return
     */
    public String resolvePlaceholder(String value, Map<String, Object> properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = buffer.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = buffer.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        while (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = buffer.substring(startIdx + 2, stopIdx);
            String propVal = Func.toStr((properties.get(propKey)));
            buffer.replace(startIdx, stopIdx + 1, propVal);
            //重置指针
            startIdx = buffer.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
            stopIdx = buffer.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        }
        return buffer.toString();
    }

}
