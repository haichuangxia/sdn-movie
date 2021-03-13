package com.neo4j.example.movie.controllers;

import com.neo4j.example.movie.domains.abstractEntity.AbstractEntity;
import com.neo4j.example.movie.repositories.PageableRepository;
import com.neo4j.example.movie.service.PageableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author qingbin 2020/12/31
 * usage 接收pageNo和PageSize
 */
@RestController
@RequestMapping("/page")
public class PageController {
    @Autowired
    private PageableRepository pageableRepository;
    @Autowired
    private PageableService pageableService;
    @GetMapping("findAll")
    public Page<AbstractEntity> findAll(@RequestParam("page") int page,@RequestParam(value = "size",defaultValue = "20") int size){
        return pageableService.findAll(page, size);
    }
    @GetMapping("find")
    public Iterable<AbstractEntity> find(@RequestParam("page") int page,@RequestParam(value = "size",defaultValue = "20") int size){
        return pageableRepository.findAll();
    }
    @GetMapping("findAllNodes")
    public Iterable<AbstractEntity> findAllNodes(){
        return pageableRepository.findAllNotes();
    }
}
