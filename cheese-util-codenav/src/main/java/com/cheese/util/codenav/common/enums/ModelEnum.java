package com.cheese.util.codenav.common.enums;

import java.io.Serializable;

/**
 * 模板枚举
 * @author sobann
 */
public enum ModelEnum implements Serializable {
    /**
     * controller
     */
    CONTROLLER("controller","controller.java.vm","Controller.java"),
    /**
     * entity
     */
    ENTITY("entity","entity.java.vm",".java"),
    /**
     * vo
     */
    ENTITY_VO("entity","vo.java.vm","VO.java"),
    /**
     * dto
     */
    ENTITY_DTO("entity","dto.java.vm","DTO.java"),
    /**
     * wrapper
     */
    WRAPPER("wrapper","wrapper.java.vm","Wrapper.java"),
    /**
     * service
     */
    SERVICE("service","service.java.vm","Service.java"),
    /**
     * serviceImpl
     */
    SERVICE_IMPL("service","serviceImpl.java.vm","ServiceImpl.java"),
    /**
     * mapper
     */
    MAPPER("mapper","mapper.java.vm","Mapper.java"),
    /**
     * mapper.xml
     */
    MAPPER_XML("mapper","mapper.xml.vm","Mapper.xml");

    private final String type;
    private final String vm;
    private final String fileSuffix;

    ModelEnum(String type, String vm, String fileSuffix) {
        this.type = type;
        this.vm = vm;
        this.fileSuffix = fileSuffix;
    }

    public String getType() {
        return type;
    }

    public String getVm() {
        return vm;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }
}
