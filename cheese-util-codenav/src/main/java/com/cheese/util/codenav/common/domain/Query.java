package com.cheese.util.codenav.common.domain;

import java.io.Serializable;

/**
 * 分页信息
 *
 * @author sobann
 */
public class Query implements Serializable {
    private Integer current;

    private Integer size;

    public Query() {
    }

    public Query(Integer current, Integer size) {
        this.current = current;
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Query{" +
                "current=" + current +
                ", size=" + size +
                '}';
    }
}
