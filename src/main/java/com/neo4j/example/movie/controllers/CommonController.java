package com.neo4j.example.movie.controllers;

import com.neo4j.example.movie.domains.AbstractNodeEntity;
import com.neo4j.example.movie.pojo.Node;
import com.neo4j.example.movie.pojo.Paths;
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

    @GetMapping("labels")
    public Iterable<String> findAllLabels() {
        return nodeRepository.getAllLabels();
    }

    @GetMapping("pro")
    public Map<String, List<String>> pro() {
        return commonService.getPro();
    }

    /**
     * 查找页面的元数据
     *
     * @return 页面元数据
     */
    @GetMapping("/graph/meta")
    public List<HashMap<String, Object>> meta() {
        return nodeRepository.getMeta();
    }

    /**
     * 主页，显示所有的节点和关系
     *
     * @return
     */
    @GetMapping("/index")
    public HashMap<String, Object> graph() {
        HashMap resultMap = new HashMap<String, Object>();
        resultMap.put("relationships", nodeRepository.getAllRelationships());
        resultMap.put("nodes", nodeRepository.getAllNotes());
        return resultMap;
    }

    /**
     * 展示某个节点相邻的节点和关系,提供信息栏目，显示相邻节点和关系的类型，前端筛选不同的节点和类型
     *
     * @param id
     * @param degree
     * @return
     */
    @GetMapping("/show/neighbors")
    public List<AbstractNodeEntity> showNeighbors(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "degree", required = false, defaultValue = "1") int degree) {
        return nodeRepository.getRelatedNodes(id, degree);
    }

    /**
     * 查找两个节点之间的最短路径
     *
     * @param starId
     * @param endId
     * @param degree
     * @return
     */
    @GetMapping("/find/shortestPath")
    public Paths getSortestPath(@RequestParam("startId") Long starId, @RequestParam("endId") Long endId, @RequestParam(value = "degree", defaultValue = "4") int degree) {
        return commonService.getShortestPath(starId, endId, degree);
    }


    /**
     * 查找两个节点之间的全部路径
     *
     * @param starId
     * @param endId
     * @param degree
     * @return
     */
    @GetMapping("/find/paths")
    public List<HashMap<String, Object>> getPath(@RequestParam("startId") Long starId, @RequestParam("endId") Long endId, @RequestParam(value = "degree", defaultValue = "4") int degree) {
        return nodeRepository.getPaths(starId, endId);
    }

    @GetMapping("/find/nodes")
    public HashMap<Object, Object> getRelationshipAndNodes(@RequestParam("id") Long id, @RequestParam(value = "degree", defaultValue = "1", required = false) int degree, @RequestParam(value = "relationShipType", defaultValue = "*", required = false) String relationshipType) {
        return nodeRepository.getRelationshipAndNodes(id, degree, relationshipType);
    }

    @GetMapping("/find/node")
    public Iterable<Node> findNode(@RequestParam("label") String label, @RequestParam("property") String property, @RequestParam("keyword") String keyword) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        return commonService.searchNode(label, property, keyword);
    }

    @GetMapping("labelsAndProperties")
    public HashMap<String, Object> getLabelsAndProperties() {
        return commonService.getLabelsAndProperties();
    }
}
