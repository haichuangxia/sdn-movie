package com.neo4j.example.movie.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author qingbin 2020/11/15
 * usage 进行一些文件的操作
 */
public class FileReader {
    /**
     * 创建并一个Properties文件对象
     * @return 返回properties文件对象
     * @throws IOException
     */
    public static Properties getProperties() throws IOException {
        Properties properties=new Properties();
        InputStream inputStream=Properties.class.getClassLoader().getResourceAsStream("cypher.properties");
        properties.load(inputStream);
        return properties;
    }
}
