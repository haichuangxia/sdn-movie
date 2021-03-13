package com.neo4j.example.movie.controllers;

import com.neo4j.example.movie.domains.abstractEntity.AbstractNodeEntity;
import com.neo4j.example.movie.pojo.Path;
import com.neo4j.example.movie.repositories.NodeRepository;
import com.neo4j.example.movie.repositories.RelationshipRepository;
import com.neo4j.example.movie.service.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qingbin 2020/11/13
 * usage 实现对一般节点的查找而不是针对某一类特定的节点
 */
@RestController
@RequestMapping("/")
public class CommonController {
    private final RelationshipRepository relationshipRepository;
    private final NodeRepository nodeRepository;
    private final CommonService commonService;

    public CommonController(RelationshipRepository relationshipRepository, NodeRepository nodeRepository, CommonService commonService) {
        this.relationshipRepository = relationshipRepository;
        this.nodeRepository = nodeRepository;
        this.commonService = commonService;
    }


    /**
     * 展示某个节点相邻的节点和关系,提供信息栏目，显示相邻节点和关系的类型，前端筛选不同的节点和类型
     *
     * @param id
     * @return 周围节点
     */
    @GetMapping("/show/neighbors")
    public Iterable<AbstractNodeEntity> showNeighbors(@RequestParam(value = "id", required = true) Long id) {
        return nodeRepository.findRelatedNode(id);
    }

    /**
     * 在一定的跳数内查找两个节点之间的最短路径
     *
     * @param starId 起始节点
     * @param endId 终止节点
     * @param degree 度
     * @return 最短路径
     */
    @GetMapping("/find/shortestPath")
    public List<Path> getSortestPath(@RequestParam("startId") Long starId, @RequestParam("endId") Long endId, @RequestParam(value = "degree", defaultValue = "4") int degree) {
        return commonService.findShortestPath(starId, endId, degree);
    }


    /**
     * 查找两个节点之间的全部路径
     *
     * @param starId 起始
     * @param endId 终止
     * @param degree 度
     * @return 所有路径
     */
    @GetMapping("/find/paths")
    public Iterable<Path> getPath(@RequestParam("startId") Long starId, @RequestParam("endId") Long endId, @RequestParam(value = "degree", defaultValue = "4") int degree) {
        return nodeRepository.findPaths(starId, endId);
    }

    /**
     * 查找一定的深度之内，指定关系类型的节点
     * @param id
     * @param degree
     * @param relationshipType
     * @return
     */
    @GetMapping("/find/nodes")
    public Iterable<AbstractNodeEntity> findNodesByRel(@RequestParam("id") Long id, @RequestParam(value = "degree", defaultValue = "1", required = false) int degree, @RequestParam(value = "relationShipType", defaultValue = "*", required = false) String relationshipType) {
        return nodeRepository.findNodeByRel(id, degree, relationshipType);
    }

    /**
     * 实现高级查询功能（通过构造cypher语句，使用apoc.cypher来执行）
     * @param label 节点的标签
     * @param property 属性
     * @param keyword 关键词
     * @return 返回节点
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     */
    @GetMapping("/find/node")
    public Iterable<HashMap<String, Object>> findNode(@RequestParam("label") String label, @RequestParam("property") String property, @RequestParam("keyword") String keyword) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        return commonService.searchNode(label, property, keyword);
    }

    /**
     * 使用模糊查询查询节点
     * @param keyword
     * @return
     */
    @GetMapping("find/nodeByKey")
    public Iterable<AbstractNodeEntity> findNodeByKeyword(String keyword) {
        return nodeRepository.findNodeByKeyword(keyword);
    }

    @GetMapping("pro")
    public Map<String, List<String>> pro() {
        return commonService.getPro();
    }

}
