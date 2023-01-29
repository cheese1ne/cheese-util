package com.cheese.util.itextpdf.demo.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.cheese.util.itextpdf.demo.model.SectionModel;
import com.cheese.util.itextpdf.handler.BaseTableHandler;
import com.cheese.util.itextpdf.handler.ITableHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 断面表格处理器
 *
 * @author sobann
 */
public class SectionHandler extends BaseTableHandler<SectionModel> implements ITableHandler {

    private final Pattern sectionCreate = Pattern.compile("^section:(\\w+\\-?)+:create$");

    public SectionHandler() {
        super(SectionModel.class);
    }

    @Override
    public boolean match(String type) {
        return sectionCreate.matcher(type).matches();
    }

    @Override
    protected void assembleData(Object param) {
        Random random = new Random();
        //模拟查询数据并构成head、foot、content数据，
        Map<String, Object> headFoot = MapUtil.createMap(LinkedHashMap.class);
        for (String field : getHeadArr()) {
            headFoot.put(field, field + random.nextInt(10));
        }
        for (String field : getFootArr()) {
            headFoot.put(field, field + random.nextInt(12));
        }
        //head foot应该是查询的数据
        this.assembleData(headFoot);

        List<Map<String, Object>> content = CollUtil.newArrayList();
//        for (int i = 0; i < 4; i++) {
        //设置50个数据测试分页效果
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map = MapUtil.createMap(LinkedHashMap.class);
            for (String field : getDataArr()) {
                //一般filed查询的属性名称，这里
                map.put(field, field + random.nextInt(100));
            }
            content.add(map);
        }
        this.assembleData(content);
    }

    @Override
    protected String[] getHeadArr() {
        //头部
        return new String[]{
                "stcdnm", "sectionName", "sectionLocation", "measureNum", "stcdCode", "riverBasin",
                "drainage", "riverName", "site", "startTime", "endTime", "wind_level", "wind_direction",
                "baseLine", "soundingMethod", "distanceMethod", "description", "rulerNum", "rulerReadStart",
                "rulerReadEnd", "zeroElevation", "waterPosStart", "waterPosEnd", "waterChangeDiff", "calPos"
        };
    }

    @Override
    protected String[] getFootArr() {
        return new String[]{"measureUsers", "calUsers", "auditUsers"};
    }

    @Override
    protected String[] getDataArr() {
        //目前数据行处理的顺序需要按照此数据定义的顺序，故存储时候使用linked hash
        //后续改造TableProperty时候，此处定义的数据需要与name的属性一致
        return new String[]{
                "pointCount", "distance", "useDepth", "bottomElevation",
                "levelPos", "waterWidth", "posDiff", "area", "wetArea", "waterRadius"
        };
    }


}
