package com.neo4j.example.movie.repositories;

import com.neo4j.example.movie.domains.abstractEntity.AbstractNodeEntity;
import com.neo4j.example.movie.pojo.Path;
import com.neo4j.example.movie.pojo.Relationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author qingbin 2020/11/12
 * usage 查找一个节点的相关节点
 */
@Repository
public interface NodeRepository extends Neo4jRepository<AbstractNodeEntity, Long> {
//路径查询

    /**
     * 最短路径查询
     * 当前还不支持指定最短路径的最大长度
     * 应该可以指定两个节点之间最短路径关系的类型
     *
     * @param startId 起始节点的id
     * @param endId   终点id
     * @param degree  最短路径度最大度
     * @return 需要研究下映射机制，确定每个cypher语句返回的数值类型
     */
    @Query("match p=shortestpath((n)-[*..4]-(m)) where id(n)=$startId AND id(m)=$endId return nodes(p) as nodes,relationships(p) as relationships,length(p) as length")
    List<Path> findShortestPath(Long startId, Long endId, int degree);

    /**
     * 路径查询
     *
     * @param startId
     * @param endId
     * @return
     */
    @Query("match p=(n)-[*..4]-(m) where id(n)=$startId AND id(m)=$endId return nodes(p) as nodes,relationships(p) as relationships,length(p) as length")
    Iterable<Path> findPaths(@Param("startId") Long startId, @Param("endId") Long endId);

//节点查询

    /**
     * 相邻节点查询
     * @param id     节点的id,最新版只支持$Param的方式限定参数，不再支持{}方式
     * @return
     */
    @Query("match p=(n)-[]-(m) where id(n)=$id return m")
    List<AbstractNodeEntity> findRelatedNode(@Param("id") Long id);

    /**
     * 使用高级查询的方式动态查询节点或者标签
     *
     * @return 返回所有查询到的结果
     */
    @Query("call apoc.cypher.run($cql,null) yield value return value.id as id,value.type as type,value.properties as properties")
    Iterable<HashMap<String, Object>> findNodes(@Param("cql") String cql);

    /**
     * 使用关键字查询节点（模糊查询）
     *
     * @param keyword
     * @return
     */
    @Query("call apoc.search.node({Movie:['title','tagline','released'],Person:['name','born']},'contains',$keyword)")
    Iterable<AbstractNodeEntity> findNodeByKeyword(String keyword);


    //图查询
    @Query("call apoc.meta.data yield label,property")
    Iterable<HashMap<String, String>> pro();


    /**
     * 返回neo4j中的所有节点
     *
     * @return 返回这个图
     */
    @Query("match(n) return n")
    Iterable<AbstractNodeEntity> getAllNotes();

    /**
     * 返回所有的关系，之所以单独查询关系，一方面是为了好返回json，另一方面可能有独立节点，可能用match(n)-[r]-()会漏掉独立节点
     *
     * @return 返回所有的关系
     */
    @Query("match(s)-[r]-(t) return id(r) as id,id(s) as source,id(t) as target,type(r) as type,properties(r) as properties")
    Iterable<Relationship> getAllRelationships();

    /**
     * 相邻节点查询
     *
     * @param id               待查询节点的id
     * @param degree           要查询的度
     * @param relationshipType 需要查询的关系类型
     * @return 返回路径
     */
    @Query("match p=(n)-[r]-(m) where id(n)=$id return p")
    Iterable<AbstractNodeEntity> findNodeByRel(@Param("id") Long id, @Param(value = "degree") int degree, @Param("relationshipType") String relationshipType);
}
