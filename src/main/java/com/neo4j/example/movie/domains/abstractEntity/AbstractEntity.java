package com.neo4j.example.movie.domains.abstractEntity;

import org.neo4j.driver.types.Entity;

/**
 * @author qingbin 2020/11/21
 * usage 抽象的实体类
 */
public abstract class AbstractEntity implements Entity{
    long id;

    @Override
    public long id(){
        return this.id;
    }

}
