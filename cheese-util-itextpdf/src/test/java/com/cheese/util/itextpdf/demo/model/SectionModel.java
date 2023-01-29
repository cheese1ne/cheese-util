package com.cheese.util.itextpdf.demo.model;

import com.cheese.util.itextpdf.anno.TableModel;
import com.cheese.util.itextpdf.anno.TableProperty;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;


/**
 * 断面计算表，数据自外部接口返回
 *
 * @author sobann
 */
@TableModel(title = "${stcdnm}站 大 断 面 计 算 表", colnums = 10, colWidth = {3.5f, 3.5f, 3.5f, 3.5f, 3.5f, 3.5f, 3.5f, 3.5f, 3.5f, 3.5f})
public class SectionModel {

    //表头
    /**
     * 断面名称及位置
     */
    @TableProperty(isHeader = true, name = "断面名称及位置: ${sectionName} - ${sectionLocation}", colSpan = 10, align = Element.ALIGN_LEFT, border = Rectangle.NO_BORDER)
    private String sectionName;
    /**
     * 施 策 号 数
     */
    @TableProperty(isHeader = true, name = "施 策 号 数: ${measureNum}", colSpan = 5, align = Element.ALIGN_LEFT, border = Rectangle.NO_BORDER)
    private String measureNum;
    /**
     * 测站编码
     */
    @TableProperty(isHeader = true, name = "测 站 编 码: ${stcdCode}", colSpan = 5, align = Element.ALIGN_LEFT, border = Rectangle.NO_BORDER)
    private String equipCode;
    /**
     * 流域
     */
    @TableProperty(isHeader = true, name = "流 域: ${riverBasin}", colSpan = 3, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.LEFT + Rectangle.BOTTOM)
    private String ly;
    /**
     * 水系
     */
    @TableProperty(isHeader = true, name = "水 系: ${drainage}", colSpan = 3, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM)
    private String sx;
    /**
     * 河名
     */
    @TableProperty(isHeader = true, name = "河 名: ${riverName}", colSpan = 4, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM + Rectangle.RIGHT)
    private String hm;
    /**
     * 地点
     */
    @TableProperty(isHeader = true, name = "地 点: ${site}", colSpan = 10, align = Element.ALIGN_LEFT)
    private String dd;
    /**
     * 施 策 时 间
     */
    @TableProperty(isHeader = true, name = "施 策 时 间: ${startTime} - ${endTime}", colSpan = 7, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.LEFT + Rectangle.BOTTOM)
    private String measureTime;
    /**
     * 风 向 风 力
     */
    @TableProperty(isHeader = true, name = "风 向 风 力: ${wind_level}${wind_direction}", colSpan = 3, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM + Rectangle.RIGHT)
    private String windPro;
    /**
     * 基 线 位 置
     */
    @TableProperty(isHeader = true, name = "基 线 位 置: ${baseLine}", colSpan = 2, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.LEFT + Rectangle.BOTTOM)
    private String baseLine;
    /**
     * 测 深 方 法
     */
    @TableProperty(isHeader = true, name = "测 深 方 法: ${soundingMethod}", colSpan = 3, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM)
    private String soundingMethod;
    /**
     * 起 点 距 测 法
     */
    @TableProperty(isHeader = true, name = "起 点 距 测 法: ${distanceMethod}", colSpan = 2, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM)
    private String distanceMethod;
    /**
     * 描述
     */
    @TableProperty(isHeader = true, name = "${description}", colSpan = 3, align = Element.ALIGN_LEFT)
    private String description;
    /**
     * 水尺编号
     */
    @TableProperty(isHeader = true, name = "水 尺 编 号: ${rulerNum}", colSpan = 3, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.LEFT + Rectangle.BOTTOM)
    private String rulerNum;
    /**
     * 水尺读数
     */
    @TableProperty(isHeader = true, name = "水 尺 读 数: 始-${rulerReadStart} 终-${rulerReadEnd}", colSpan = 4, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM)
    private String rulerRead;
    /**
     * 零点高程
     */
    @TableProperty(isHeader = true, name = "零 点 高 程: ${zeroElevation}", colSpan = 3, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM + Rectangle.RIGHT)
    private String zeroElevation;
    /**
     * 水 位(m)
     */
    @TableProperty(isHeader = true, name = "水 位(m): 始-${waterPosStart} 终-${waterPosEnd}", colSpan = 4, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.LEFT + Rectangle.BOTTOM)
    private String pos;
    /**
     * 水 位 涨 落 数
     */
    @TableProperty(isHeader = true, name = "水 位 涨 落 数: ${waterChangeDiff}", colSpan = 3, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM)
    private String waterChangeDiff;
    /**
     * 计 算 水 位(m)
     */
    @TableProperty(isHeader = true, name = "计 算 水 位(m): ${calPos}", colSpan = 3, align = Element.ALIGN_LEFT, border = Rectangle.TOP + Rectangle.BOTTOM + Rectangle.RIGHT)
    private String calPos;
    /**
     * 测 量 记 载
     */
    @TableProperty(isHeader = true, name = "测 量 记 载", colSpan = 4)
    private String record;
    /**
     * 各 分 级 水 位 特 征 值 计 算
     */
    @TableProperty(isHeader = true, name = "各 分 级 水 位 特 征 值 计 算", colSpan = 6)
    private String traitCal;
    /**
     * 点次
     */
    @TableProperty(isHeader = true, name = "点 次")
    private String pointCount;
    /**
     * 起点距
     */
    @TableProperty(isHeader = true, name = "起点距(m)")
    private String distance;
    /**
     * 应用水深
     */
    @TableProperty(isHeader = true, name = "应用水深(m)")
    private String useDepth;
    /**
     * 河底高程
     */
    @TableProperty(isHeader = true, name = "河底高程(m)")
    private String bottomElevation;
    /**
     * 分级水位
     */
    @TableProperty(isHeader = true, name = "分级水位(m)")
    private String levelPos;
    /**
     * 水面宽
     */
    @TableProperty(isHeader = true, name = "水面宽(m)")
    private String waterWidth;
    /**
     * 位高差
     */
    @TableProperty(isHeader = true, name = "位高差(m)")
    private String posDiff;
    /**
     * 面积
     */
    @TableProperty(isHeader = true, name = "面积(m²)")
    private String area;
    /**
     * 湿周
     */
    @TableProperty(isHeader = true, name = "湿周")
    private String wetArea;
    /**
     * 水力半径
     */
    @TableProperty(isHeader = true, name = "水力半径(m)")
    private String waterRadius;

    //数据行
    @TableProperty(isContent = true, name = "${pointCount}")
    private String dc;

    @TableProperty(isContent = true, name = "${distance}")
    private String qdj;

    @TableProperty(isContent = true, name = "${useDepth}")
    private String yyss;

    @TableProperty(isContent = true, name = "${bottomElevation}")
    private String hdgc;

    @TableProperty(isContent = true, name = "${levelPos}")
    private String fjsw;

    @TableProperty(isContent = true, name = "${waterWidth}")
    private String smk;

    @TableProperty(isContent = true, name = "${posDiff}")
    private String wgc;

    @TableProperty(isContent = true, name = "${area}")
    private String mj;

    @TableProperty(isContent = true, name = "${wetArea}")
    private String sz;

    @TableProperty(isContent = true, name = "${waterRadius}")
    private String slbj;

    //表尾
    /**
     * 测量
     */
    @TableProperty(isFooter = true, name = "测量: ${measureUsers}", colSpan = 3, border = Rectangle.NO_BORDER)
    private String measureUsers;
    /**
     * 计算
     */
    @TableProperty(isFooter = true, name = "计算: ${calUsers}", colSpan = 3, border = Rectangle.NO_BORDER)
    private String calUsers;
    /**
     * 校核
     */
    @TableProperty(isFooter = true, name = "校核: ${auditUsers}", colSpan = 4, border = Rectangle.NO_BORDER)
    private String auditUsers;
}
