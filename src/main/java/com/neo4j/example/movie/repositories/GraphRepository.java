package com.neo4j.example.movie.repositories;

import com.neo4j.example.movie.domains.abstractEntity.AbstractNodeEntity;
import com.neo4j.example.movie.pojo.Graph;
import com.neo4j.example.movie.pojo.Relationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.HashMap;

/**
 * @author qingbin 2021/3/6
 * usage 用于图的元数据等信息的查询
 */
public interface GraphRepository extends Neo4jRepository {
    /**
     * @return 数据库所有的标签
     */
    @Query("call db.labels")
    Iterable<String> getAllLabels();

    /**
     * @return 数据库中所有的property的key值
     */
    @Query("call db.propertyKeys")
    Iterable<String> propertyKeys();

    /**
     * 获取schema，得到各种labels的relationship，count和properties等信息
     *
     * @return
     */
    @Query("call apoc.meta.schema")
    HashMap<String, Object> schema();

    /*查询数据库的现状*/
//    yield labelCount ,relTypeCount,propertyKeyCount,nodeCount,relCount,labels,relTypes,relTypesCount
    @Query("call apoc.meta.stats  yield stats")
    HashMap<String, Object> stats();

    /*查询所有节点*/
    @Query("match(n) return n")
    Iterable<AbstractNodeEntity> getAllNodes();

    /**
     * 返回所有的关系，之所以单独查询关系，一方面是为了好返回json，另一方面可能有独立节点，可能用match(n)-[r]-()会漏掉独立节点
     *
     * @return 返回所有的关系
     */
    @Query("match(s)-[r]-(t) return id(r) as id,id(s) as source,id(t) as target,type(r) as type,properties(r) as properties")
    Iterable<Relationship> getAllRelationships();

    @Query("call apoc.")
    Iterable<Graph> graph();
}
