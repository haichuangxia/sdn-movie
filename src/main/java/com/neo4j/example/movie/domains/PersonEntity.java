package com.neo4j.example.movie.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jennifer Reif
 */
@NodeEntity("Person")
public class PersonEntity extends AbstractNodeEntity {
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
    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public List<Role> getActedIn() { return actedIn; }

    public void setActedIn(List<Role> movies) { this.actedIn = movies; }

    public List<MovieEntity> getDirected() { return directed; }

    public void setDirected(List<MovieEntity> directed) { this.directed = directed; }
}
