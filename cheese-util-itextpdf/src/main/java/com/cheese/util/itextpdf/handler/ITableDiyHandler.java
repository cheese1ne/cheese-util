package com.cheese.util.itextpdf.handler;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

/**
 * 断面录入pdf定制化处理方案 业务接口
 *
 * @author sobann
 */
public interface ITableDiyHandler {

    default void createDiyHeader(Document document) throws DocumentException{
    }

    default void  createDiyContent(Document document) throws DocumentException{
    }

    default void createDiyFooter(Document document) throws DocumentException{
    }

    default boolean needTableTitle(){
        return true;
    }

    default boolean needTablePage(){
        return true;
    }
}
