package com.neo4j.example.movie.domains.nodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neo4j.example.movie.domains.abstractEntity.AbstractNodeEntity;
import com.neo4j.example.movie.domains.relationshipEntity.Role;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jennifer Reif
 */
@NodeEntity("Person")
@Data
public class PersonEntity extends AbstractNodeEntity {
    private String category = "Person";
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Property("born")
    private int birthyear;

    @JsonIgnore
    @JsonIgnoreProperties("person")
    @org.neo4j.ogm.annotation.Relationship(type = "ACTED_IN")
    private List<Role> actedIn = new ArrayList<>();

    @JsonIgnore
    @JsonIgnoreProperties({"actors", "directors"})
    @Relationship(type = "DIRECTED")
    private List<MovieEntity> directed = new ArrayList<>();

    public PersonEntity() {
    }

    public PersonEntity(String name, int birthyear) {
        this.name = name;
        this.birthyear = birthyear;
    }
}
