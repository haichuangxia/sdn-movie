package com.neo4j.example.movie.pojo;

import lombok.Data;
import org.neo4j.driver.Value;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Map;
import java.util.function.Function;

/**
 * @author qingbin 2020/12/1
 * usage
 */
@QueryResult
@Data
public class Node implements org.neo4j.driver.types.Node {
    @Property("identity")
    private Long id;
    @Property("labels")
    private String[] labels;
    private Map<String, Object> properties;

    @Override
    public Iterable<String> labels() {
        return null;
    }

    @Override
    public boolean hasLabel(String s) {
        return false;
    }

    @Override
    public long id() {
        return 0;
    }

    @Override
    public Iterable<String> keys() {
        return null;
    }

    @Override
    public boolean containsKey(String s) {
        return false;
    }

    @Override
    public Value get(String s) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Value> values() {
        return null;
    }

    @Override
    public <T> Iterable<T> values(Function<Value, T> function) {
        return null;
    }

    @Override
    public Map<String, Object> asMap() {
        return null;
    }

    @Override
    public <T> Map<String, T> asMap(Function<Value, T> function) {
        return null;
    }
}
