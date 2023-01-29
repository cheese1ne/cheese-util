package com.cheese.util.itextpdf.anno;

/**
 * 注解配置抽提封装类
 *
 * @author sobann
 */
public class ModelEntity {
    private int colNums;
    private int currentPage = 1;
    private int totalPage = 1;
    private int dataCountPer;
    private float[] widthArr;
    private String title;
    private String subTitle;
    private boolean diyHead;
    private boolean diyContent;
    private boolean diyFoot;

    public ModelEntity(TableModel model) {
        this.colNums = model.colnums();
        this.dataCountPer = model.dataCountPer();
        this.widthArr = model.colWidth();
        this.title = model.title();
        this.subTitle = model.subTitle();
        this.diyHead = model.diyHeader();
        this.diyContent = model.diyContent();
        this.diyFoot = model.diyFooter();
    }

    public int getColNums() {
        return colNums;
    }

    public void setColNums(int colNums) {
        this.colNums = colNums;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getDataCountPer() {
        return dataCountPer;
    }

    public void setDataCountPer(int dataCountPer) {
        this.dataCountPer = dataCountPer;
    }

    public float[] getWidthArr() {
        return widthArr;
    }

    public void setWidthArr(float[] widthArr) {
        this.widthArr = widthArr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public boolean isDiyHead() {
        return diyHead;
    }

    public void setDiyHead(boolean diyHead) {
        this.diyHead = diyHead;
    }

    public boolean isDiyContent() {
        return diyContent;
    }

    public void setDiyContent(boolean diyContent) {
        this.diyContent = diyContent;
    }

    public boolean isDiyFoot() {
        return diyFoot;
    }

    public void setDiyFoot(boolean diyFoot) {
        this.diyFoot = diyFoot;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
