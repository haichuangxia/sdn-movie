package com.neo4j.example.movie.repositories;

import com.neo4j.example.movie.domains.AbstractNodeEntity;
import com.neo4j.example.movie.pojo.Node;
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
    //图查询
    @Query("call apoc.meta.data yield label,property")
    Iterable<HashMap<String, String>> pro();

    /**
     * @return 数据库所有的标签
     */
    @Query("call db.labels")
    Iterable<String> getAllLabels();

    /**
     * @return 书库节点所有的属性
     */
    @Query("call db.propertyKeys yield propertyKey as property")
    Iterable<String> getPropertySet();

    /**
     * 获取数据库的元数据，节点和关系的类型和数量
     * 这样的Cypher语句太长，应该可以另外建立一个文件，专门存放Cypher语句
     *
     * @return 从数据库的Response来看，使用List比较适合
     */
    @org.springframework.data.neo4j.annotation.Query("call apoc.meta.stats() yield labels,relTypesCount")
    List<HashMap<String, Object>> getMeta();

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
    @Query("match p=shortestpath((n)-[*..4]-(m)) where id(n)=$startId AND id(m)=$endId return distinct labels(m),count(*)")
    List<HashMap<String, Object>> getShortestPathMeta(Long startId, Long endId, int degree);

    @Query("match p=shortestpath((n)-[*..4]-(m)) where id(n)=$startId AND id(m)=$endId return nodes(p) as nodes")
    List<HashMap<String, Object>> getShortestPathNodes(Long startId, Long endId, int degree);

    @Query("match p=shortestpath((n)-[*..4]-(m)) where id(n)=$startId AND id(m)=$endId return distinct relationships(p)")
    List<HashMap<String, Object>> getShortestPathRelationships(Long startId, Long endId, int degree);

    /**
     * 路径查询
     *
     * @param startId
     * @param endId
     * @return
     */
    @Query("match p=(n)-[*..4]-(m) where id(n)=$startId AND id(m)=$endId return nodes(p)")
    List<HashMap<String, Object>> getPaths(@Param("startId") Long startId, @Param("endId") Long endId);

//节点查询

    /**
     * 获取一定跳数内节点的相关节点
     *
     * @param id     节点的id,最新版只支持$Param的方式限定参数，不再支持{}方式
     * @param degree 要查找的度
     * @return
     */
    @Query("match p=(n)-[r]-(m) where id(n)=$id return m")
    List<AbstractNodeEntity> getRelatedNodes(@Param("id") Long id, @Param("degree") int degree);

    /**
     * 相邻节点查询
     *
     * @param id               待查询节点的id
     * @param degree           要查询的度
     * @param relationshipType 需要查询的关系类型
     * @return 返回路径
     */
    @Query("match p=(n)-[r]-(m) where id(n)=1 return p")
    HashMap<Object, Object> getRelationshipAndNodes(@Param("id") Long id, @Param(value = "degree") int degree, @Param("relationshipType") String relationshipType);


    /**
     * 动态查询节点或者标签
     *
     * @return 返回所有查询到的结果
     */
    @Query("call apoc.cypher.run($cql,null) yield value return value.id as id,value.type as type,value.properties as properties")
    Iterable<Node> findNodes(@Param("cql") String cql);

}
