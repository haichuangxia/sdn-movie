package com.neo4j.example.movie.service;

import com.neo4j.example.movie.pojo.Node;
import com.neo4j.example.movie.pojo.Paths;
import com.neo4j.example.movie.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author qingbin 2020/11/18
 * usage 用来拼接字符串，实现动态查询
 */
@Service
public class CommonService {
    private static String cql;
    private static String basescql;
    @Autowired
    private NodeRepository nodeRepository;

    public Paths getShortestPath(Long startId, Long endId, int hop) {
        Paths paths = new Paths();
        paths.setLabels(nodeRepository.getShortestPathMeta(startId, endId, hop));
        paths.setNodes(nodeRepository.getShortestPathNodes(startId, endId, hop));
        paths.setRelationships(nodeRepository.getShortestPathRelationships(startId, endId, hop));
        return paths;
    }

    /**
     * 根据输入的参数动态查找节点
     *
     * @param label    查找节点的标签
     * @param property 节点的属性
     * @param keyword  关键词
     */
    public Iterable<Node> searchNode(String label, String property, String keyword) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        basescql = "match(n:%s) where n.%s=\"%s\" return id(n) as id,labels(n) as type,properties(n) as properties";
        cql = String.format(basescql, label, property, keyword);
        return nodeRepository.findNodes(cql);
    }

    /**
     * 可以实现合并多个字典相同key的功能
     *
     * @return
     */
    public HashMap<String, List<String>> getPro() {
        Iterable<HashMap<String, String>> origal = nodeRepository.pro();//获取原始数据
        //创建新的容器来容纳
        HashMap<String, List<String>> map = new HashMap<>();
        //遍历原始元素
        origal.forEach(item -> {
            String label = item.get("label");
            List<String> value = new ArrayList<>();
            if (map.containsKey(label)) {//判断子元素的标签是否已经在map的key中
                map.get(label).add(item.get("property"));
            } else {
                value.add(item.get("property"));
                map.put(label, value);
            }
        });
        return map;
    }

    public HashMap<String, Object> getLabelsAndProperties() {
        HashMap<String, Object> labelsAndPropertiesSet = new HashMap<>();
        labelsAndPropertiesSet.put("labels", nodeRepository.getAllLabels());
        labelsAndPropertiesSet.put("properties", nodeRepository.getPropertySet());
        return labelsAndPropertiesSet;
    }
}
