package com.cheese.util.itextpdf.manager;

import cn.hutool.core.collection.CollUtil;
import com.cheese.core.tool.util.Func;
import com.cheese.util.itextpdf.handler.ITableHandler;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 成果pdf处理管理器
 *
 * @author sobann
 */
//@Component
public class TableResultManager {

//    @Autowired(required = false)
    private List<ITableHandler> tableHandlers;

    public void addTableHandlers(ITableHandler... handlers) {
        if (Func.isEmpty(tableHandlers)) {
            this.tableHandlers = CollUtil.newArrayList();
        }
        this.tableHandlers.addAll(Arrays.asList(handlers));
    }

    /**
     * 针对指定类型的pdf文件流处理
     *
     * @param param
     * @param type
     * @param outputStream
     * @throws IOException
     * @throws DocumentException
     */
    public void handle(Object param, final String type, OutputStream outputStream) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfCopy copy = new PdfCopy(document, outputStream);
        document.open();
        for (ITableHandler handler : tableHandlers) {
            //类型匹配
            if (handler.match(type)) {
                byte[] chunk = handler.handle(param);
                if (Func.isEmpty(chunk)) continue;
                final PdfReader reader = new PdfReader(chunk);
                int pages = reader.getNumberOfPages();
                for (int i = 1; i <= pages; i++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, i);
                    copy.addPage(page);
                }
            }
        }
        document.close();
    }
}
