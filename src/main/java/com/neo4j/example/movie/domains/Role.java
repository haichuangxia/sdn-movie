package com.neo4j.example.movie.domains;

import org.neo4j.ogm.annotation.RelationshipEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;
import java.util.ArrayList;
import java.util.List;

@RelationshipEntity(value = "ACTED_IN")
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

    public Long getId() {
        return id;
    }

    public List<String> getRoles() { return roles; }

    public PersonEntity getPerson() {
        return personEntity;
    }

    public MovieEntity getMovie() {
        return movieEntity;
    }

}
