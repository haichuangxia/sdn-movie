package com.neo4j.example.movie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author qingbin 2020/11/22
 * usage
 */
public class SimpleTest {
    @Test
    public void string(){
        String label="movie";
        String property="title";
        String keyword="The Matrix";
        String basescql = "match(n:%s) where n.%s=\"%s\" return id(n) as id,labels(n) as type,properties(n) as properties";
        String cql = String.format(basescql, label, property, keyword);
        System.out.println(cql);
    }
    @Test
    public void session(){
        HashMap<String,List<String>> map1=new HashMap<>();
        List<String> value=new ArrayList<>();
        map1.put("Movie",value);
        map1.get("Movie").add("tittle");
        map1.get("Movie").add("born");
        for(String str : map1.get("Movie")){
            System.out.println(str);
        }

        for (int i=0;i<5;i++){

        }
    }
}
