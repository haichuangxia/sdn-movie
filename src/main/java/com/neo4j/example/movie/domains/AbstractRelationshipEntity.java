package com.neo4j.example.movie.domains;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

/**
 * @author qingbin 2020/11/19
 * usage a public class of all kind of relationship entities
 */
public abstract class AbstractRelationshipEntity extends AbstractEntity{
    @Id
    @GeneratedValue
    private Long id;
}
