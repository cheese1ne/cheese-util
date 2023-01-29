package com.cheese.util.codenav.modules.targetnav.handler.normal;

import com.cheese.core.tool.util.DateUtil;
import com.cheese.core.tool.util.Func;
import com.cheese.core.tool.util.IoUtil;
import com.cheese.core.tool.util.StringUtil;
import com.cheese.util.codenav.common.constant.BusinessConstant;
import com.cheese.util.codenav.common.domain.ColumnEntity;
import com.cheese.util.codenav.common.domain.GenerateEntity;
import com.cheese.util.codenav.common.domain.TableEntity;
import com.cheese.util.codenav.modules.setting.entity.FieldTypeMapConfig;
import com.cheese.util.codenav.modules.setting.entity.TemplateConfig;
import com.cheese.util.codenav.modules.setting.service.IFieldTypeMapConfigService;
import com.cheese.util.codenav.modules.setting.service.ITemplateConfigService;
import com.cheese.util.codenav.modules.targetnav.entity.dto.ColumnInfoDTO;
import com.cheese.util.codenav.modules.targetnav.entity.dto.TableInfoDTO;
import com.cheese.util.codenav.modules.targetnav.handler.ICodenavHandler;
import com.cheese.util.codenav.modules.targetnav.mapper.TargetMapper;
import com.cheese.util.codenav.util.CodeUtil;
import com.cheese.util.codenav.util.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 普通业务类处理器父类实现，子类完成模板方法
 * @author sobann
 */
public abstract class NormalHandler implements ICodenavHandler {

    @Autowired
    protected IFieldTypeMapConfigService fieldTypeMapConfigService;
    @Autowired
    protected ITemplateConfigService templateConfigService;
    @Autowired
    protected TargetMapper targetMapper;

    /**
     * 匹配规则:以normal开头的业务
     */
    private Pattern normalPattern = Pattern.compile("^normal");

    @Override
    public byte[] handle(Long uid, GenerateEntity generateEntity) {
        recordUserLog(uid);
        return gainDataBytes(generateEntity);
    }

    @Override
    public boolean match(String type) {
        return normalPattern.matcher(type).matches();
    }

    /**
     * 处理用户日志
     * @param uid
     * @return
     */
    public abstract void recordUserLog(Long uid);

    /**
     * 获取数据流
     * @param generateEntity
     * @return
     */
    public abstract byte[] gainDataBytes(GenerateEntity generateEntity);

    /**
     * 单张表代码逆向输出
     *
     * @param tableName
     * @param generateEntity
     * @return
     */
    protected byte[] generate(String tableName, GenerateEntity generateEntity) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        TableEntity tableEntity = this.assembleTableEntity(tableName, generateEntity);
        //封装模板中填充的属性字段
        Map<String, Object> context = this.assembleVelocityContext(tableEntity, generateEntity);
        VelocityContext velocityContext = VelocityUtil.initContext(context);
        //获取模板文件配置信息
        List<TemplateConfig> templateConfigs = templateConfigService.selectListByTypes(generateEntity.getTemplates());
        try {
            for (TemplateConfig templateConfig : templateConfigs) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(templateConfig.getTempaltePath(), "UTF-8");
                tpl.merge(velocityContext, sw);
                //创建ZipEntry添加到zip流中
                zip.putNextEntry(new ZipEntry(CodeUtil.getCodeFileName(templateConfig.getFileName(), tableEntity.getClassName(), generateEntity.getModulesPackage())));
                IoUtil.write(sw.toString(),zip, StandardCharsets.UTF_8);
                //按顺序关闭流，避免出现不可预料的文件末端问题
                zip.closeEntry();
                IoUtil.closeQuietly(sw);
            }
            IoUtil.closeQuietly(zip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回流对象
        return outputStream.toByteArray();
    }

    /**
     * 组装tableEntity，主要是表基本属性
     *
     * @param tableName
     * @param generateEntity
     * @return
     */
    private TableEntity assembleTableEntity(String tableName, GenerateEntity generateEntity) {
        TableInfoDTO tableInfoDTO = targetMapper.queryTable2DTO(tableName);
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(tableInfoDTO.getTableName());
        tableEntity.setComments(tableInfoDTO.getTableComment());
        // 表名转换成Java类名
        String className = CodeUtil.tableToJava(tableEntity.getTableName(), generateEntity.getTablePrefix());
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtil.unCapitalize(className));
        //绑定数据列
        this.assembleColumnEntityList(tableName, tableEntity);
        //数据列绑定完成后，若无主键列，将首个字段设置为主键
        if (tableEntity.getPrimaryKey() == null) {
            tableEntity.setPrimaryKey(tableEntity.getColumns().get(0));
        }
        return tableEntity;
    }

    /**
     * 组装tableEntity，主要是列属性
     *
     * @param tableName
     * @param tableEntity
     */
    private void assembleColumnEntityList(String tableName, TableEntity tableEntity) {
        List<ColumnInfoDTO> columnInfos = targetMapper.queryColumns2DTO(tableName);
        List<ColumnEntity> columnList = new ArrayList<>();
        for (ColumnInfoDTO columnInfo : columnInfos) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(columnInfo.getColumnName());
            columnEntity.setDataType(columnInfo.getDataType());
            columnEntity.setComments(columnInfo.getColumnComment());
            columnEntity.setExtra(columnInfo.getExtra());
            //数据库列名转换成驼峰Java属性名，
            String attrName = CodeUtil.columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtil.unCapitalize(attrName));
            //数据库列的数据类型，通过资源配置转换为Java数据类型
            FieldTypeMapConfig fieldTypeMapConfig = fieldTypeMapConfigService.selectByDataType(columnEntity.getDataType());
            columnEntity.setAttrType(Func.isEmpty(fieldTypeMapConfig) ? BusinessConstant.UNKNOWN_TYPE : fieldTypeMapConfig.getJavaType());
            //标记数据实体的主键
            if (BusinessConstant.PRIMARY.equalsIgnoreCase(columnInfo.getColumnKey()) && tableEntity.getPrimaryKey() == null) {
                tableEntity.setPrimaryKey(columnEntity);
            }
            columnList.add(columnEntity);
        }
        tableEntity.setColumns(columnList);
    }

    /**
     * velocity配置信息组装
     * @param tableEntity
     * @param generateEntity
     * @return
     */
    private Map<String, Object> assembleVelocityContext(TableEntity tableEntity, GenerateEntity generateEntity) {
        Map<String, Object> map = new HashMap<>(32);
        map.put(BusinessConstant.TABLE_NAME, tableEntity.getTableName());
        map.put(BusinessConstant.COMMENTS, tableEntity.getComments());
        map.put(BusinessConstant.PRIMARY_KEY, tableEntity.getPrimaryKey());
        map.put(BusinessConstant.UP_CLASS_NAME, tableEntity.getClassName());
        map.put(BusinessConstant.LOW_CLASS_NAME, tableEntity.getClassname());
        map.put(BusinessConstant.PATH_NAME, tableEntity.getClassname().toLowerCase());
        map.put(BusinessConstant.COLUMNS, tableEntity.getColumns());
        map.put(BusinessConstant.PACKAGE, generateEntity.getModulesPackage());
        map.put(BusinessConstant.AUTHOR, generateEntity.getAuthor());
        map.put(BusinessConstant.CODE_DATE, DateUtil.format(new Date(), "yyyy-MM-dd"));
        map.put(BusinessConstant.JDK_VERSION, generateEntity.getJdkVersion());
        map.put(BusinessConstant.CODE_VERSION, generateEntity.getCodeVersion());
        return map;
    }

}
