package com.neo4j.example.movie.repositories;

import com.neo4j.example.movie.domains.abstractEntity.AbstractNodeEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @author qingbin 2020/11/20
 * usage
 */
@Repository
public interface RelationshipRepository extends Neo4jRepository<AbstractNodeEntity,Long> {
    @Query("match()-[Role:ACTED_IN]-() return Role")
    public Iterable<HashMap<String,Object>> getAllRelationships();

}
