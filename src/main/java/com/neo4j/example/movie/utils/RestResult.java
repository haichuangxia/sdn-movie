package com.neo4j.example.movie.utils;

/**
 * @author qingbin 2020/11/11
 * usage 封装REST风格的架构
 */
public class RestResult {
    /* 返回状态码 */
    private int code;
    /* 返回业务数据 */
    private Object data;
    /* 返回业务信息 */
    private String msg;

    public RestResult(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
