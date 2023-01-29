package com.cheese.util.itextpdf.demo;

import com.cheese.util.itextpdf.demo.handler.SectionHandler;
import com.cheese.util.itextpdf.manager.TableResultManager;
import com.itextpdf.text.DocumentException;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * demo
 *
 * @author sobann
 */
public class Cheese1 {

    @Test
    public void createTableTest() throws IOException, DocumentException {
        TableResultManager tableCreateManager = new TableResultManager();
        tableCreateManager.addTableHandlers(new SectionHandler());
        //指定测试文件地址
        FileOutputStream out = new FileOutputStream("/Users/sobann/Downloads/section.pdf");
        tableCreateManager.handle(null, "section:50:create", out);
    }
}
