package com.neo4j.example.movie.pojo;

import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qingbin 2020/11/21
 * usage 用来接收返回得参数，将参数与Java对象建立映射
 * @QueryResult 表明要将这个类同返回结果建立映射
 * 这个需要cypher语句使用as,将返回得结果同这个对象的的属性建立映射
 */
@QueryResult
public class Relationship {
    private Long id;
    private Long source;
    private Long target;
    private String type;
    private Map<String, Object> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public void setProperties(HashMap<String, Object> properties) {
        this.properties = properties;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }
}
