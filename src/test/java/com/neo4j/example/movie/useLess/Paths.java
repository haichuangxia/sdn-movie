package com.neo4j.example.movie.useLess;

import java.util.HashMap;
import java.util.List;

/**
 * @author qingbin 2020/11/23
 * usage
 */
public class Paths {
    private List<HashMap<String,Object>> labels;
    private List<HashMap<String,Object>> nodes;
    private List<HashMap<String,Object>> relationships;

    public Paths(){

    }
    public Paths(List<HashMap<String, Object>> labels, List<HashMap<String, Object>> nodes, List<HashMap<String, Object>> relationships) {
        this.labels = labels;
        this.nodes = nodes;
        this.relationships = relationships;
    }

    public List<HashMap<String, Object>> getLabels() {
        return labels;
    }

    public void setLabels(List<HashMap<String, Object>> labels) {
        this.labels = labels;
    }

    public List<HashMap<String, Object>> getNodes() {
        return nodes;
    }

    public void setNodes(List<HashMap<String, Object>> nodes) {
        this.nodes = nodes;
    }

    public List<HashMap<String, Object>> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<HashMap<String, Object>> relationships) {
        this.relationships = relationships;
    }
}
