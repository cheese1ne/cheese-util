package com.cheese.util.itextpdf.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.cheese.core.tool.util.Func;
import com.cheese.util.itextpdf.anno.ModelEntity;
import com.cheese.util.itextpdf.anno.PropertyEntity;
import com.cheese.util.itextpdf.anno.TableModel;
import com.cheese.util.itextpdf.anno.TableProperty;
import com.cheese.util.itextpdf.exception.IncorrectPropertyException;
import com.cheese.util.itextpdf.util.PdfUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * tip:
 * 1.可以基于assembleData方法完成对headData、footData、contentList数据的封装
 * 2.默认数据行为25行，若有其他需要，通过TableModel.dataCountPer属性进行定义
 * 3.占位符目前用于替换PropertyEntity中name属性中的占位符，不同位置的占位符定义需要在getXxxArr中定义
 * 4.如果表名出现占位符，定义在getHeadArr中
 * 5.默认创建表名和分页，分页属性中的最大页数计算根据contentListData完成
 * 6.具体使用参考测试demo cheese1
 * <p>
 *
 * @author sobann
 */
public abstract class BaseTableHandler<T> extends CommonPropSupport implements ITableDiyHandler {


    //头部数据
    protected Map<String, Object> headData = MapUtil.newHashMap();
    //脚部数据
    protected Map<String, Object> footData = MapUtil.newHashMap();
    //主体数据
    protected List<Map<String, Object>> contentListData = CollUtil.newArrayList();

    protected Class<T> clazz;
    protected ModelEntity modelEntity;
    protected List<PropertyEntity> headList = CollUtil.newArrayList();
    protected List<PropertyEntity> footList = CollUtil.newArrayList();
    protected List<PropertyEntity> contentList = CollUtil.newArrayList();
    //填充数据
    protected Map<String, Object> fillBlankMap;

    public BaseTableHandler(Class<T> clazz) {
        this.clazz = clazz;
        TableModel model = clazz.getAnnotation(TableModel.class);
        Assert.notNull(model, "TableModel must not empty");
        this.modelEntity = new ModelEntity(model);
        //表格配置解析
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            TableProperty property = field.getAnnotation(TableProperty.class);
            if (Func.isEmpty(property)) continue;
            //通常配置属性尽量不要在多个地方使用，明确使用位置
            if (property.isHeader() && property.isFooter() && property.isContent()) {
                throw new IncorrectPropertyException(property.name());
            }
            if (!property.isHeader() && !property.isFooter() && !property.isContent()) {
                throw new IncorrectPropertyException(property.name());
            }
            PropertyEntity propertyEntity = new PropertyEntity(property);
            if (property.isHeader()) headList.add(propertyEntity);
            if (property.isContent()) contentList.add(propertyEntity);
            if (property.isFooter()) footList.add(propertyEntity);
        }
        //空行填充的两种方式，一种通过数据行填充，一种通过尾行配置填充空单元格
        fillBlankMap = this.fillBlankMap();
    }

    @Override
    public byte[] handle(Object param) {
        //数据获取与组装
        this.assembleData(param);
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            //分页处理，除数据行外的其他数据占用a4页固定，故通过判断当前数据列进行换页操作
            int allPages = modelEntity.getTotalPage();
            while (allPages != 0) {
                //0.特殊处理 表名上边距
                Paragraph space = new Paragraph(" ");
                space.setSpacingAfter(25);
                document.add(space);

                //1.表名
                if (this.needTableTitle()) {
                    this.createTitle(document);
                }

                //2.分页信息 第几页，共几页
                if (this.needTablePage()) {
                    this.createPageInfo(document);
                }

                //3.创建表格，包括表头、数据、表尾
                //3.1：表头
                if (!modelEntity.isDiyHead()) {
                    this.createTableHeader(document);
                } else {
                    this.createDiyHeader(document);
                }

                //3.2 数据
                if (!modelEntity.isDiyContent()) {
                    this.insertDataList(document);
                } else {
                    this.createDiyContent(document);
                }
                //3.3 表尾
                if (!modelEntity.isDiyFoot()) {
                    this.createTableFooter(document);
                } else {
                    this.createDiyFooter(document);
                }
                allPages--;
                if (allPages != 0) {
                    document.newPage();
                }
            }
            //关闭文档
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return baos.toByteArray();
    }

    /**
     * 数据组装，通用
     * 特殊数据需要单独处理
     *
     * @param param
     */
    protected abstract void assembleData(Object param);

    //数据处理 头尾
    protected void assembleData(Map<String, Object> headFootData) {
        Map<String, Object> head = MapUtil.createMap(LinkedHashMap.class);
        Map<String, Object> foot = MapUtil.createMap(LinkedHashMap.class);
        for (String headField : getHeadArr()) {
            head.put(headField, headFootData.get(headField));
        }
        for (String footField : getFootArr()) {
            foot.put(footField, headFootData.get(footField));
        }
        this.headData = head;
        this.footData = foot;
    }

    //数据处理 数据行
    protected void assembleData(List<Map<String, Object>> contentDataList) {
        //遍历并取出表格中需要的字段
        List<Map<String, Object>> result = CollUtil.newArrayList();
        for (Map<String, Object> originData : contentDataList) {
            Map<String, Object> data = MapUtil.createMap(LinkedHashMap.class);
            for (String dataField : getDataArr()) {
                data.put(dataField, originData.get(dataField));
            }
            result.add(data);
        }
        this.contentListData = result;
        int count = contentDataList.size() / modelEntity.getDataCountPer();
        int totalPage = contentDataList.size() % modelEntity.getDataCountPer() == 0 ? count : count + 1;
        this.modelEntity.setTotalPage(totalPage);
    }

    //创建抬头
    protected void createTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph();
        Font _FONT = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 10f, Font.BOLD, BaseColor.BLACK);
        _FONT.setSize(14);

        String titleName = modelEntity.getTitle();
        String titleNameVal = resolver.resolvePlaceholder(titleName, headData);

        Chunk chunkL = new Chunk(titleNameVal, _FONT);
        title.add(chunkL);

        Font _FONT2 = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 10f, Font.BOLD, BaseColor.BLACK);
        _FONT2.setSize(10);

        String subTitleName = modelEntity.getSubTitle();
        String subTitleNameVal = resolver.resolvePlaceholder(subTitleName, headData);

        Chunk chunkR = new Chunk(subTitleNameVal, _FONT2);
        title.add(chunkR);

        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(6);
        document.add(title);
    }

    //分页信息
    protected void createPageInfo(Document document) throws DocumentException {
        Font _FONT = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 10f, Font.BOLD, BaseColor.BLACK);
        _FONT.setSize(8);
        Paragraph pageInfo = new Paragraph("第" + modelEntity.getCurrentPage() + "页共" + modelEntity.getTotalPage() + "页", _FONT);
        pageInfo.setIndentationRight(30);
        pageInfo.setAlignment(Element.ALIGN_RIGHT);
        pageInfo.setSpacingAfter(3);
        document.add(pageInfo);
    }

    //表头信息
    protected void createTableHeader(Document document) throws DocumentException {
        Font _FONT = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 10f, Font.NORMAL, BaseColor.BLACK);
        _FONT.setSize(8);
        PdfPTable headTable = new PdfPTable(modelEntity.getColNums());
        headTable.setWidthPercentage(100);
        headTable.setTotalWidth(modelEntity.getWidthArr());
        for (PropertyEntity propertyEntity : headList) {
            String name = propertyEntity.getName();
            String val = resolver.resolvePlaceholder(name, headData);
            PdfPCell cell = PdfUtil.createMergeCell(val, _FONT, propertyEntity.getRowSpan(), propertyEntity.getColSpan(),
                    12 * propertyEntity.getRowSpan(), 0.05f, Rectangle.TOP + Rectangle.LEFT);
            cell.setHorizontalAlignment(propertyEntity.getAlign());
            cell.setBorder(propertyEntity.getBorder());
            headTable.addCell(cell);
        }
        document.add(headTable);
    }

    //数据信息
    protected void insertDataList(Document document) throws DocumentException {
        PdfPTable dataTable = new PdfPTable(modelEntity.getWidthArr().length);
        dataTable.setWidthPercentage(100);
        dataTable.setTotalWidth(modelEntity.getWidthArr());

        final Font dataFont = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 10f, Font.NORMAL, BaseColor.BLACK);
        dataFont.setSize(8);
        //当列表数据较多时，对数据表进行切割
        List<Map<String, Object>> insertDatas = contentListData;
        if (contentListData.size() > modelEntity.getDataCountPer()) {
            int currentPage = modelEntity.getCurrentPage();
            insertDatas = CollUtil.sub(contentListData, (currentPage - 1) * modelEntity.getDataCountPer(), currentPage * modelEntity.getDataCountPer());
            currentPage++;
            modelEntity.setCurrentPage(currentPage);
        }
        //补足空行
        while (insertDatas.size() < modelEntity.getDataCountPer()) {
            insertDatas.add(fillBlankMap);
        }
        //外循环循环每一行需要插入的数据
        for (Map<String, Object> data : insertDatas) {
            //内循环循环解析对应位置的数据属性值并创建单元格
            for (PropertyEntity propertyEntity : contentList) {
                String name = propertyEntity.getName();
                String val = resolver.resolvePlaceholder(name, data);
                PdfPCell cell = PdfUtil.createMergeCell(val, dataFont, propertyEntity.getRowSpan(), propertyEntity.getColSpan(),
                        12 * propertyEntity.getRowSpan(), 0.05f, propertyEntity.getBorder());
                dataTable.addCell(cell);
            }
        }
        document.add(dataTable);
    }

    //表尾信息
    protected void createTableFooter(Document document) throws DocumentException {
        Font _FONT = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 10f, Font.NORMAL, BaseColor.BLACK);
        PdfPTable footTable = new PdfPTable(modelEntity.getWidthArr().length);
        footTable.setWidthPercentage(100);
        footTable.setTotalWidth(modelEntity.getWidthArr());
        // 循环生成头信息
        for (PropertyEntity propertyEntity : footList) {
            String name = propertyEntity.getName();
            String val = resolver.resolvePlaceholder(name, footData);
            PdfPCell cell = PdfUtil.createMergeCell(val, _FONT, propertyEntity.getRowSpan(), propertyEntity.getColSpan(),
                    12 * propertyEntity.getRowSpan(), 0.05f, Rectangle.TOP + Rectangle.LEFT);
            cell.setHorizontalAlignment(propertyEntity.getAlign());
            cell.setBorder(propertyEntity.getBorder());
            footTable.addCell(cell);
        }
        document.add(footTable);
    }

    //补足空行，系数为0.75
    private Map<String, Object> fillBlankMap() {
        Map<String, Object> blankMap = new HashMap<>(modelEntity.getColNums());
        for (int i = 0; i < modelEntity.getColNums(); i++) {
            blankMap.put(String.valueOf(i), null);
        }
        return blankMap;
    }

}
