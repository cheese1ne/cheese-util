package com.cheese.util.itextpdf.exception;

/**
 * 表格属性错误的配置异常
 *
 * @author sobann
 */
public class IncorrectPropertyException extends RuntimeException{

    public IncorrectPropertyException(String propertyName){
        super("error table property val set, the propertyName is" + propertyName);
    }
}
