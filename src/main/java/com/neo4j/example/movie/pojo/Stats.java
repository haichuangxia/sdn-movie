package com.neo4j.example.movie.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.HashMap;

/**
 * @author qingbin 2021/3/7
 * usage 元数据
 */
@Data
@QueryResult
public class Stats {
    private Long labelCount;
//    private int relTypeCount;
//    private int propertyKeyCount;
//    private int nodeCount;
//    private int relCount;
//    private HashMap<String,Object> labels;
//    private HashMap<String,Object> relTypes;
//    private HashMap<String,Object> relTypesCount;
//    private Stats stats;
}
