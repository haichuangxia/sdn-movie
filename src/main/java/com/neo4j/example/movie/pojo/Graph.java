package com.neo4j.example.movie.pojo;

import com.neo4j.example.movie.domains.abstractEntity.AbstractEntity;
import com.neo4j.example.movie.domains.abstractEntity.AbstractNodeEntity;
import lombok.Data;

/**
 * @author qingbin 2021/3/10
 * usage
 */
@Data
public class Graph {
    private Iterable<AbstractNodeEntity> nodes;
    private Iterable<Relationship> relationships;

    public Graph(Iterable<AbstractNodeEntity> nodes, Iterable<Relationship> relationships) {
        this.nodes = nodes;
        this.relationships = relationships;
    }
}
