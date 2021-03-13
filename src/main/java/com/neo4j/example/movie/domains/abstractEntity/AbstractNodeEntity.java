package com.neo4j.example.movie.domains.abstractEntity;

import com.neo4j.example.movie.domains.abstractEntity.AbstractEntity;
import org.neo4j.driver.Value;

import java.util.Map;
import java.util.function.Function;

/**
 * @author 23860
 */
public abstract class AbstractNodeEntity extends AbstractEntity {
    private Long id;

    public AbstractNodeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractNodeEntity(Long id) {
        this.id = id;
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
