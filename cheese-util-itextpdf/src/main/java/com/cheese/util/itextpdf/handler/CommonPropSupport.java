package com.cheese.util.itextpdf.handler;


import com.cheese.util.itextpdf.resolver.PlaceholderStringValueResolver;
import org.springframework.core.io.ClassPathResource;

/**
 * 通用属性提供器
 * 1.提供数据字段: 表头、数据行以及表尾
 * 2.提供中文字体
 * 3.提供占位符解析器
 *
 * @author sobann
 */
public abstract class CommonPropSupport {

    //起始页，默认为1，根据查询的数据分页完成步进
    protected static final String fontPath;
    //表达式解析器
    protected static final PlaceholderStringValueResolver resolver;

    static {
        //pdf字体加载
        fontPath = new ClassPathResource("/font/simsun.ttf").getPath();
        resolver = new PlaceholderStringValueResolver();
    }

    protected abstract String[] getHeadArr();

    protected abstract String[] getFootArr();

    protected abstract String[] getDataArr();

    //数据处理
    public byte[] handle(Object param) {
        return new byte[0];
    }

}
