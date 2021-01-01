package com.neo4j.example.movie.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;
import java.util.ArrayList;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

/**
 * @author Jennifer Reif
 * 注意NodeEntity区分大小写
 */
@NodeEntity("Movie")
public class MovieEntity extends com.neo4j.example.movie.domains.AbstractNodeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private int released;
    @Property("tagline")
    private String description;

    /**
     * 使用@JsonIgnoreProperties注解忽略类或者字段的某些属性，这里用在字段上，表示忽略字段的movie属性
    * 注意只是忽略而不是删除
    * */
    @JsonIgnoreProperties("movie")
    @JsonIgnore
    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private List<Role> actors = new ArrayList<>();
    /**
     * @JsonIgnore 避免两个互相引用的类之间造成无限递归调用的问题
     */
    @JsonIgnore
    @JsonIgnoreProperties({"actedIn", "directed"})
    @Relationship(type = "DIRECTED", direction = INCOMING)
    private List<com.neo4j.example.movie.domains.PersonEntity> directors = new ArrayList<>();

    public MovieEntity(String title, int released, String tagline) {
        super();
        this.title = title;
        this.released = released;
        this.description = tagline;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getActors() {
        return actors;
    }

    public void setActors(List<Role> actors) {
        this.actors = actors;
    }

    public List<com.neo4j.example.movie.domains.PersonEntity> getDirectors() {
        return directors;
    }

    public void setDirectors(List<com.neo4j.example.movie.domains.PersonEntity> directors) {
        this.directors = directors;
    }
}
