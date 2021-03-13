package com.neo4j.example.movie.repositories;

import com.neo4j.example.movie.domains.nodeEntity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.HashMap;
import java.util.List;

/**
 * @author Jennifer Reif
 */
public interface MovieRepository extends Neo4jRepository<MovieEntity, Long>, PagingAndSortingRepository<MovieEntity,Long> {

    MovieEntity getMovieByTitle(String title);

    Iterable<MovieEntity> findMovieByTitleLike(String title);

    @Query("call apoc.search.node({Movie:['title','tagline','released']},'contains',$keyword)")
    Iterable<MovieEntity> findMovieEntityByKeyword(String keyword);

    @Query("match(n:Movie) return n.title,n.tagline")
    List<HashMap<String,Object>> findTest();

    @Override
    Page<MovieEntity> findAll(Pageable pageable);
}
