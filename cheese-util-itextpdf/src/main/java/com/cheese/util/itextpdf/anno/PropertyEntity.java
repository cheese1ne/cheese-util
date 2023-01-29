package com.cheese.util.itextpdf.anno;

/**
 * 注解配置抽提封装类
 *
 * @author sobann
 */
public class PropertyEntity {

    private String name;

    private int rowSpan;

    private int colSpan;

    private int align;

    private int border;

    public PropertyEntity(TableProperty property) {
        this.name = property.name();
        this.rowSpan = property.rowSpan();
        this.colSpan = property.colSpan();
        this.align = property.align();
        this.border = property.border();
    }

    //todo TableFooterProperty

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }
}
