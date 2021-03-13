package com.neo4j.example.movie.domains.nodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neo4j.example.movie.domains.abstractEntity.AbstractNodeEntity;
import com.neo4j.example.movie.domains.relationshipEntity.Role;
import lombok.Data;
import org.neo4j.driver.Value;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

/**
 * @author Jennifer Reif
 * 注意NodeEntity区分大小写
 */
@NodeEntity("Movie")
@Data
public class MovieEntity extends AbstractNodeEntity {
    private String category = "Movie";
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private int released;
//    @Property("tagline") @Property注解用于类字段与数据库中字段不一致的情况
    private String tagline;
    /**
     * 使用@JsonIgnoreProperties注解忽略类或者字段的某些属性，这里用在字段上，表示忽略字段的movie属性
     * 注意只是忽略而不是删除
     */
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
    private List<PersonEntity> directors = new ArrayList<>();

    public MovieEntity(String title, int released, String tagline) {
        super();
        this.title = title;
        this.released = released;
        this.tagline = tagline;
    }

    @Override
    public Iterable<String> keys() {
        List<String> key=new ArrayList<>();
        key.add("title");
        key.add("released");
        key.add("tagline");
        return key;
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
