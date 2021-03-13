package com.neo4j.example.movie.repositories;

import com.neo4j.example.movie.domains.abstractEntity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author qingbin 2020/12/20
 * usage 集成PagingAndSortingRepository接口，增加分页功能
 * PagingAndSortingRepository<AbstractEntity, Long>,
 */
@Repository
public interface PageableRepository extends  Neo4jRepository<AbstractEntity, Long>,PagingAndSortingRepository<AbstractEntity,Long> {
    @Override
    Iterable<AbstractEntity> findAll(Sort sort);

    @Override
    @Query(value="match(n) return n",countQuery = "match(n) return count(*)",exists = false)
    Page<AbstractEntity> findAll(Pageable pageable);

    @Override
    Iterable<AbstractEntity> findAll();

    @Query(value = "match(n) return n")
    Page<AbstractEntity> findNodes(Pageable pageable);

    @Query("match(n) return n")
    Iterable<AbstractEntity> findAllNotes();
}
