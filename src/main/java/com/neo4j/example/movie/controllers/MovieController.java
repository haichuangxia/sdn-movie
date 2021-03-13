package com.neo4j.example.movie.controllers;

import com.neo4j.example.movie.domains.nodeEntity.MovieEntity;
import com.neo4j.example.movie.repositories.MovieRepository;
import com.neo4j.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Jennifer Reif
 */
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public Iterable<MovieEntity> findAllNodes() {
        return movieRepository.findAll();
    }

    @GetMapping("/test")
    public List<HashMap<String, Object>> test(){
        return movieRepository.findTest();
    }

    @GetMapping("/{title}")
    public MovieEntity getMovieByTitle(@PathVariable String title) {
        return movieRepository.getMovieByTitle(title);
    }

    @GetMapping("/search/{title}")
    public Iterable<MovieEntity> findMovieByTitleLike(@PathVariable String title) {
        return movieRepository.findMovieByTitleLike(title);
    }
    @GetMapping("/findAll")
    public Page<MovieEntity> findAll(){
        return movieService.findAll();
    }
}
