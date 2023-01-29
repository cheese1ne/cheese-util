package com.cheese.util.codenav.util;


import com.cheese.util.codenav.common.constant.BusinessConstant;
import com.cheese.util.codenav.common.constant.NormalCharConstant;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 以此工具类为原型，设计可插拔式的代码生成脚手架
 *
 * @author sobann
 */
public class CodeUtil {

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    public static String getPackagePath() {
        return BusinessConstant.MAIN + File.separator + BusinessConstant.JAVA + File.separator;
    }

    public static String getCodeFileName(String fileName, String className, String modulesPackage) {
        //功能固定格式：main/java/package/file
        String packagePath = getPackagePath();
        if (StringUtils.isNotBlank(modulesPackage)) {
            packagePath += modulesPackage.replace(NormalCharConstant.DOT, File.separator) + File.separator;
        }
        //模板名称配置在数据库中：entity/%s.java
        packagePath += String.format(fileName, className);
        return packagePath;
    }
}
