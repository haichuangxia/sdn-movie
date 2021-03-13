package com.neo4j.example.movie.pojo;


import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author qingbin 2021/2/21
 * usage
 */
@Data
@QueryResult
public class Path {
    private Long length;
    private Object nodes;
    private Object relationships;
}
