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
        String v="world";
        String k="Hello %s";
        String result=String.format(k,v);
        System.out.println(result);
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
