package com.neo4j.example.movie.pojo;

import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * @author qingbin 2021/3/7
 * usage
 */
@Data
@QueryResult
public class Meta {
    Iterable<String> labels;
}
