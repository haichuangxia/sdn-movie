package com.neo4j.example.movie.service;

import com.neo4j.example.movie.domains.nodeEntity.MovieEntity;
import com.neo4j.example.movie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author qingbin 2020/12/31
 * usage
 */
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public Page<MovieEntity> findAll(){
        Pageable pageable= PageRequest.of(5,5);
        return movieRepository.findAll(pageable);
    }
}
