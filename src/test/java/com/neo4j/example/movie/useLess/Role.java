package com.neo4j.example.movie.useLess;

import com.neo4j.example.movie.domains.abstractEntity.AbstractRelationshipEntity;
import com.neo4j.example.movie.domains.nodeEntity.MovieEntity;
import com.neo4j.example.movie.domains.nodeEntity.PersonEntity;
import lombok.Data;
import org.neo4j.driver.Value;
import org.neo4j.ogm.annotation.RelationshipEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RelationshipEntity(value = "ACTED_IN")
@Data
public class Role extends AbstractRelationshipEntity {
    @Id
    @GeneratedValue
    private Long id;

    private List<String> roles = new ArrayList<>();

    @StartNode
    @JsonIgnoreProperties({"actedIn", "directed"})
    private PersonEntity personEntity;

    @EndNode
    @JsonIgnoreProperties({"actors", "directors"})
    private MovieEntity movieEntity;

    public Role() {
    }

    public Role(PersonEntity personEntity, MovieEntity movieEntity) {
        super();
        this.personEntity = personEntity;
        this.movieEntity = movieEntity;
    }

    @Override
    public long startNodeId() {
        return this.personEntity.id();
    }

    @Override
    public long endNodeId() {
        return this.movieEntity.id();
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public boolean hasType(String s) {
        return false;
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
