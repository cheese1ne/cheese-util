## cheese-util-itextpdf使用说明

#### 一、表格属性配置(定义数据模型)

- `TableModel`配置项
  
  ```markdown
    1.通过@TableModel定义表格的基本属性
    2.通过@TableProperty定义每一个单元格的基本属性
    (详细请参考demo中的SectionModel)
  ```

****

#### 二、定义表格处理器TableHandler

- `TableHandler`定义内容
  ```markdown
  1.处理器匹配规则
  2.处理器针对配置属性中占位符映射，与配置项中的属性占位符字段一一对应
  3.自定义数据获取和组装方法，最终封装至对应的数据模型中即可
  (详细请参考demo中的SectionHandler)
  ```

****

#### 三、输出pdf示例操作

- `输出操作方式`
  ```markdown
  //(详细请参考demo中的Cheese1)
  TableResultManager tableCreateManager = new TableResultManager();
  tableCreateManager.addTableHandlers(new ITableHandler(){...});
  tableCreateManager.handle("查询参数", "规则", "输出流");

  ```
****
#### 四、其他

- `尚未解决的问题`
  ```markdown
  目前TableResultManager使用字节流合并的方式创建单个或复数pdf文件流，
  在操作单个文件流时表格分页可正常使用，操作多个匹配类型时请关闭分页功能
  ```


  

