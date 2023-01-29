package com.cheese.util.easyexcel.common.strategy;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.cheese.util.easyexcel.common.anno.ComplexExcel;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 下拉框策略
 *
 * @author sobann
 */
public class DefaultCustomSpinnerStrategy<T> extends CustomSpinnerStrategy<T> {

    public DefaultCustomSpinnerStrategy(Map<Integer, String[]> mapDropDown, int firstRow, int lastRow) {
        super(mapDropDown, firstRow, lastRow);
    }

    public DefaultCustomSpinnerStrategy(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        super.beforeSheetCreate(writeWorkbookHolder, writeSheetHolder);
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        super.afterSheetCreate(writeWorkbookHolder, writeSheetHolder);
    }

    @Override
    protected void init() {
        this.spinnerInit();
    }

    @Override
    protected void createRangeAddressList(Sheet sheet) {
        ///设置下拉框
        DataValidationHelper helper = sheet.getDataValidationHelper();
        for (Map.Entry<Integer, String[]> entry : this.mapDropDown.entrySet()) {
            //起始行、终止行、起始列、终止列
            CellRangeAddressList addressList = new CellRangeAddressList(this.firstRow, this.lastRow, entry.getKey(), entry.getKey());
            //设置下拉框数据
            DataValidationConstraint constraint = helper.createExplicitListConstraint(entry.getValue());
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            //处理Excel兼容性问题
            if (dataValidation instanceof XSSFDataValidation) {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            } else {
                dataValidation.setSuppressDropDownArrow(false);
            }
            sheet.addValidationData(dataValidation);
        }
    }

    /**
     * 默认数据初始化
     */
    private void spinnerInit() {
        Field[] declaredFields = this.t.getDeclaredFields();
        Map<Integer, String[]> mapDropDown = new HashMap<>(declaredFields.length);
        //下拉框数据
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(Boolean.TRUE);
            ComplexExcel annotation = declaredField.getAnnotation(ComplexExcel.class);
            if (Objects.nonNull(annotation)) {
                //列号和下拉框数据
                Integer currentCol = annotation.currentCol();
                String[] dropDownArr = annotation.dropDownArr();
                mapDropDown.putIfAbsent(currentCol, dropDownArr);
            }
        }
        this.mapDropDown = mapDropDown;
        //起始行
        int headRow = 1;
        Field[] fields = this.t.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(Boolean.TRUE);
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (Objects.nonNull(excelProperty) && excelProperty.value().length > headRow) {
                headRow = excelProperty.value().length;
            }
        }
        this.firstRow = headRow + ONE;
        this.lastRow = firstRow + TWO_THOUSAND;
    }
}
