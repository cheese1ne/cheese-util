package com.cheese.util.codenav.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 创建参数实体
 *
 * @author sobann
 */
public class GenerateEntity implements Serializable {
    private String businessType;
    private String type;
    private String tableName;
    private String tablePrefix;
    private String modulesPackage;
    private String author;
    private List<String> templates;
    private String jdkVersion;
    private String codeVersion;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getModulesPackage() {
        return modulesPackage;
    }

    public void setModulesPackage(String modulesPackage) {
        this.modulesPackage = modulesPackage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getCodeVersion() {
        return codeVersion;
    }

    public void setCodeVersion(String codeVersion) {
        this.codeVersion = codeVersion;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
