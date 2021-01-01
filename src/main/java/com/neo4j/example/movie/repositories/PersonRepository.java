package com.neo4j.example.movie.repositories;

import com.neo4j.example.movie.domains.PersonEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @author Qingbin
 */
public interface PersonRepository extends Neo4jRepository<PersonEntity, Long> {

    PersonEntity getPersonByName(String name);

    Iterable<PersonEntity> findPersonByNameLike(String name);

    @Query("MATCH (am:Movie)<-[ai:ACTED_IN]-(p:Person)-[d:DIRECTED]->(dm:Movie) return p, collect(ai), collect(d), collect(am), collect(dm)")
    List<PersonEntity> getPersonsWhoActAndDirect();
}
