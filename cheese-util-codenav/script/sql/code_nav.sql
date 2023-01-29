/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3307
 Source Schema         : code_nav

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 18/03/2022 17:35:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_field_type_map_config
-- ----------------------------
DROP TABLE IF EXISTS `code_field_type_map_config`;
CREATE TABLE `code_field_type_map_config`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `db_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库类型',
  `java_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'java类型',
  `create_user` bigint(64) NULL DEFAULT NULL COMMENT '创建者id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(64) NULL DEFAULT 1 COMMENT '编辑者id',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '编辑时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '业务状态',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of code_field_type_map_config
-- ----------------------------
INSERT INTO `code_field_type_map_config` VALUES (1, 'tinyint', 'Integer', 1, '2021-10-22 15:26:55', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (2, 'smallint', 'Integer', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (3, 'mediumint', 'Integer', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (4, 'int', 'Integer', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (5, 'integer', 'Integer', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (6, 'bigint', 'Long', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (7, 'float', 'Float', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (8, 'double', 'Double', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (9, 'decimal', 'BigDecimal', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (10, 'bit', 'Boolean', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (11, 'char', 'String', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (12, 'varchar', 'String', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (13, 'tinytext', 'String', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (14, 'text', 'String', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (15, 'mediumtext', 'String', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (16, 'longtext', 'String', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (17, 'time', 'LocalDateTime', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (18, 'date', 'LocalDate', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (19, 'datetime', 'Date', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);
INSERT INTO `code_field_type_map_config` VALUES (20, 'timestamp', 'Date', 1, '2021-10-22 15:26:59', 1, '2021-10-22 15:26:59', 0, 0);

-- ----------------------------
-- Table structure for code_template_config
-- ----------------------------
DROP TABLE IF EXISTS `code_template_config`;
CREATE TABLE `code_template_config`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板类型',
  `template_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `tempalte_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板路径',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成文件相对路径',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` bigint(64) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(64) NULL DEFAULT NULL COMMENT '编辑者',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '编辑时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '业务状态',
  `is_deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of code_template_config
-- ----------------------------
INSERT INTO `code_template_config` VALUES (1, 'entity', '实体模板', 'template/entity.java.vm', 'entity/%s.java', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);
INSERT INTO `code_template_config` VALUES (2, 'service', '服务接口模板', 'template/service.java.vm', 'service/I%sService.java', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);
INSERT INTO `code_template_config` VALUES (3, 'serviceImpl', '服务接口实现模板', 'template/serviceImpl.java.vm', 'service/impl/%sServiceImpl.java', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);
INSERT INTO `code_template_config` VALUES (4, 'mapper_java', '持久层java模板', 'template/mapper.java.vm', 'mapper/%sMapper.java', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);
INSERT INTO `code_template_config` VALUES (5, 'mapper_xml', '持久层xml模板', 'template/mapper.xml.vm', 'mapper/%sMapper.xml', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);
INSERT INTO `code_template_config` VALUES (6, 'controller', '控制层模板', 'template/controller.java.vm', 'controller/%sController.java', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);
INSERT INTO `code_template_config` VALUES (7, 'vo', '视图模板', 'template/vo.java.vm', 'entity/vo/%sVO.java', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);
INSERT INTO `code_template_config` VALUES (8, 'dto', '条件传输类模板', 'template/dto.java.vm', 'entity/dto/%sDTO.java', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);
INSERT INTO `code_template_config` VALUES (9, 'wrapper', '实体转化器模板', 'template/wrapper.java.vm', 'wrapper/%sWrapper.java', NULL, 1, '2021-10-22 15:30:46', 1, '2021-10-22 15:31:04', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
