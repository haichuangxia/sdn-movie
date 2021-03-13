package com.neo4j.example.movie.controllers;

import com.neo4j.example.movie.domains.abstractEntity.AbstractNodeEntity;
import com.neo4j.example.movie.pojo.Graph;
import com.neo4j.example.movie.pojo.Node;
import com.neo4j.example.movie.pojo.Relationship;
import com.neo4j.example.movie.pojo.Stats;
import com.neo4j.example.movie.repositories.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author qingbin 2021/3/7
 * usage 关于图的用法
 */
@RestController
@RequestMapping("/graph")
public class GraphComtroller {
    @Autowired
    private GraphRepository graphRepository;

    /*查询数据库中所有的变迁*/
    @GetMapping("labels")
    public Iterable<String> findAllLabels() {
        return graphRepository.getAllLabels();
    }

    /*获取所有的property keys*/
    @GetMapping("propertyKeys")
    public Iterable<String> propertyKeys() {
        return graphRepository.propertyKeys();
    }

    /*获取schema信息*/
    @GetMapping("schema")
    public HashMap<String, Object> schema() {
        return graphRepository.schema();
    }

    @GetMapping("stats")
    public HashMap<String,Object> status() {
        return graphRepository.stats();
    }

    @GetMapping("nodes")
    Iterable<AbstractNodeEntity> getAllNodes() {
        return graphRepository.getAllNodes();
    }

    @GetMapping("relationships")
    Iterable<Relationship> getAllRelationships() {
        return graphRepository.getAllRelationships();
    }

    /**
     * 主页，返回所有的节点和关系
     * @return
     */
    @GetMapping()
    Graph graph(){
        Graph graph=new Graph(graphRepository.getAllNodes(),graphRepository.getAllRelationships());
        return graph;
    }
}
