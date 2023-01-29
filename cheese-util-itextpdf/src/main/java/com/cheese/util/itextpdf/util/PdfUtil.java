package com.cheese.util.itextpdf.util;

import cn.hutool.core.io.resource.ClassPathResource;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * 对于itempdf工具的封装
 *
 * @author sobann
 */
public class PdfUtil {

    private static final String fontLocation = "/font/simsun.ttf";

    private static final String fontPath;
    private static final int _B_SIZE = 12;
    private static final float _B_B_WIDTH = 0.05f;
    private static final int _RECORD_FONT = 8;

    private static final int WITHOUT_RESULT = 26;
    private static final int WITH_RESULT = 20;

    static {
        //静态资源加载
        fontPath = new ClassPathResource(fontLocation).getPath();
    }


    public static Paragraph createParagraph(String text, Font font, int alignment) {
        Paragraph pageInfo = new Paragraph(text, font);
        pageInfo.setAlignment(alignment);
        return pageInfo;

    }

    /**
     * 创建自定义表格 设置列数量以及每一列的宽度
     *
     * @param colnums
     * @param colWidths
     * @return
     * @throws DocumentException
     */
    public static PdfPTable createTable(int colnums, float[] colWidths) throws DocumentException {
        //根据列数以及列宽对参数进行检查
        if (colnums <= 0 || colnums != colWidths.length) {
            throw new DocumentException("the columns: " + colnums + " does not match the length of the column width array");
        }
        PdfPTable table = new PdfPTable(colnums);
        table.setWidthPercentage(100);
        table.setTotalWidth(colWidths);
        return table;
    }


    /**
     * 创建合并的单元格，指定宽高和合并的参数
     *
     * @param text
     * @param font
     * @param rowspan
     * @param colspan
     * @param height
     * @param width
     * @param border
     * @return
     */
    public static PdfPCell createMergeCell(String text, Font font, int rowspan, int colspan, int height, float width, int border) {
        PdfPCell cell = createCell(text, font, height, width, border);
        cell.setRowspan(rowspan);
        cell.setColspan(colspan);
        cell.setPaddingTop(0);
        return cell;
    }

    /**
     * 创建文本单元格，指定宽高
     *
     * @param text
     * @param font
     * @param height
     * @param width
     * @return
     */
    public static PdfPCell createNormalCell(String text, Font font, float height, float width) {
        PdfPCell cell = createCell(text, font, height, width, Rectangle.TOP + Rectangle.LEFT);
        cell.setPaddingTop(0);
        cell.setPaddingLeft(0);
        cell.setPaddingRight(0);
        return cell;
    }

    /**
     * @param text
     * @param font
     * @return
     */
    public static PdfPCell createCell(String text, Font font, float height, float width, int border) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(height);
        cell.setBorderWidth(width);
        cell.setBorder(border);
        return cell;
    }


    /**
     * 字体样式构建封装
     *
     * @param fontName
     * @param encoding
     * @param isEmbedded
     * @param size
     * @param style
     * @param baseColor
     * @return
     */
    private static Font createFont(String fontName, String encoding, boolean isEmbedded, float size, int style, BaseColor baseColor) {
        return FontFactory.getFont(fontName, encoding, isEmbedded, size, style, baseColor);
    }
}
