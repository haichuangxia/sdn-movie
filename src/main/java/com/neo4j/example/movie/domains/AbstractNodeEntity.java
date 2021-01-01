package com.neo4j.example.movie.domains;

/**
 * @author 23860
 */
public abstract class AbstractNodeEntity extends AbstractEntity{
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
}
