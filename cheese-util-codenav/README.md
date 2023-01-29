## cheese-util-codenav使用说明

#### 一、数据源配置

- `code-nav`:配置项

  ```markdown
  1.数据库中导入code_nav.sql
  2.修改datasource-codenav.properties数据连接配置
  ```

- `target`:逆向目标库

  ```markdown
  修改datasource-target.properties数据连接配置
  ```

****

#### 二、数据库表逆向说明

- 核心接口：`localhost:8888/generate`，GET
- 参数说明
  - `type`：逆向类型：目前包含`allTable`、`oneTable`、`matchPrefix`三种类型
    - `allTable`：目标数据库全部表
    - `oneTable`：单张表，配合`tableName`使用
    - `matchPrefix`：前缀匹配的表，配合`tablePrefix`使用
  - `tableName`：表名称
  - `tablePrefix`：表前缀
  - `modulesPackage`：模块文件路径
  - `templates`：模板类型，见`code_template_config`的type类型
  - `author`：作者
  - `jdkVersion`：jdk版本
  - `codeVersion`：代码版本

****

#### 三、导出实例：

- 单表导出

  ```markdown
  http://localhost:8888/generate?templates=entity&templates=vo&templates=dto&templates=mapper_xml&templates=mapper_java&templates=service&templates=serviceImpl&templates=controller&templates=wrapper&type=oneTable&tableName=dt_statistic_user_browse_history&tablePrefix=dt_statistic&modulesPackage=com.cheese.util.codenav.modules.statistics&author=sobann&jdkVersion=1.8&codeVersion=1.0.0
  ```

- 指定前缀表导出

  ```markdown
  http://localhost:8888/generate?templates=entity&templates=vo&templates=dto&templates=mapper_xml&templates=mapper_java&templates=service&templates=serviceImpl&templates=controller&templates=wrapper&type=matchPrefix&tableName=dt_statistic_user_browse_history&tablePrefix=dt_statistic&modulesPackage=com.cheese.util.codenav.modules.statistics&author=sobann&jdkVersion=1.8&codeVersion=1.0.0
  ```

- 目标库全部表导出

  ```markdown
  http://localhost:8888/generate?templates=entity&templates=vo&templates=dto&templates=mapper_xml&templates=mapper_java&templates=service&templates=serviceImpl&templates=controller&templates=wrapper&type=allTable&tableName=dt_statistic_user_browse_history&tablePrefix=dt_statistic&modulesPackage=com.cheese.util.codenav.modules.statistics&author=sobann&jdkVersion=1.8&codeVersion=1.0.0
  ```

  

