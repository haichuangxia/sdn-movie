package com.neo4j.example.movie.pojo;

import lombok.Data;
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
@Data
public class Relationship {
    private Long id;
    private Long source;
    private Long target;
    private String type;
    private Map<String, Object> properties;
}
