## Neo4j
1. ### 基本功能
    1. #### 预览功能
        1. 图预览：如果节点过多则可以使用分页查询,需要解决一个问题:节点和边的数据如何传送的问题
        1. 属性查看：可以调用本地程序来打开属性文件(如果属性是一个文件的话,需要在前端完成)
    2. #### 查询功能
        1. 全文检索：输入一个关键词检索相关的节点和关系,这个关键词可以是节点或者关系的属性或者类型,label
        1. 查找相关节点：以一个节点为中心,查找一定的度以内的相关节点(可以限定查找的类型,如关系或者节点)
        1. 路径查询： 查找两个节点之间的路径，所有路径和最短路径
    1. 可以实现动态的对节点进行操作(可以设置下,只有管理员登陆之后才可以进行修改,否则只可以查看)
    
1. 后端接口设计
   
    1. 节点显示：
        | API | 功能 | 数据结构 |
        |---| ---|---|
        	index：返回节点，关系，和元数据，示例结构为
    
1. ### 相关配置
   
    1. #### 技术栈
        1. neo4j 4.X
        1. Idea2020.2.3
        1. spring-data-neo4j
        1. Springboot
    1. #### 数据源
        1. 使用Neo4j的示例程序提供的数据,不用手动的添加数据源
    
1. ### 待解决问题
	1. #### 全文检索的实现
	   1. ##### 如何引入ik插件，进行中文分词
	      1. [neo4j扩展](https://neo4j.com/docs/java-reference/current/extending-neo4j/)
	      2. [Neo4j插件，过程开发]（https://github.com/neo4j-examples/neo4j-procedure-template）
	      3. 调用插件定义的过程：通过包名+过程名调用，如apoc就是一个包
	      4. [插件开发教程]（https://neo4j.com/docs/java-reference/current/extending-neo4j/procedures-and-functions/）
	   3. ##### 对于非字符串类型的字段应该怎么进行检索 
	   4. ##### 内置的过程和函数应该怎么使用，怎么看参数列表
	      1.  name：：type:前面是参数名，后面是参数的类型
	1. #### 查询结果的返回值问题：
	    1. 一般限定的返回值都是实体类对象，怎么返回字符串等。返回字符串和通过对象映射返回数值有什么不同
	
1. ### 开发流程
	
	1. 数据库操作
   	
   	1. 同步数据到ElasticSearch
      	1. 在neo.conf中开启触发器
      
    2. Elasticsearch相关配置
   
	        1. 创建索引：
	               1. 命令：curl -X METOHD “协议：//IP：端口/要创建的索引名”
	               2. 注意：大小写敏感，X和METHOD类型都是大写字符
	               3. 索引名不能以大写字母开头
	        2. 创建Type
	            1. 方法：在索引名后加入一个参数就是Type的名称，通过JSON给Type添加Document
	            2. 注意：Windows下操作与Linux下有较大差别。注意看 [elasticsearch入门备忘录（curl）]（http://www.yihaomen.com/article/java/630.htm） 
	
	3. #### 创建实体,对象映射
	
	  4. ##### 创建Node实体 
	
	        1. ######  标注实体类
	              1. 使用@Node标注实体类
	        1. ###### 标注ID
	              1. @Id标注实体的id属性
	              1. @GeneratedValue配置自动生成id的策略
	              1. UUIDStringGenerator.class返回String类型的id
	
	   5. ###### 标注属性
	               1. @Property标注与Node或者relationship属性名不同的属性
	
	   6. ###### 标注关系
	               1. @Relationship标注Node的静态关系(限定关系的type),使用map类型的属性,k为另一个节点,v为关系来建立有属性的关系
	               1. 可以通过@Relationship+List来建立无属性的关系
	               1. 通过Map来定义动态关系,动态关系不能用@Relationship注解来定义
	
	   7. #####  创建Relationship属性
	        1. @RelationshipProperties: 表明这是一个Relationship的属性类,定义一个关系的所拥有的属性(不包括type或者start,end等信息) 
	
	  8. ##### Tips
	
	   9. 使用Node的Labels作为Node的type,不同type的Node成为一个实体类
	
	   10. wither:不用修改旧的,创建新的,从而使得那个属性不可变
	
	   11. 实体不用设置set方法
	
	  12. ##### 问题
	
	   13. 实体类定义的属性是有限,特定的,如果想给节点添加其他标签或者是是添加其他属性应该怎么解决.
	
	   14. 如何定义节点的关系较好,如果有孤立的节点应该怎么办
	
	   15. 关系是怎么处理的,如果要查找特定的关系怎么查找
	
	   16. 关系具有多种类型的,难道需要在实体中将所有不同类型的relationship都做定义?--通过动态关系来实现
	
	17. #### 创建Repository
	
1. ### 实验中出现的问题

    1.  使用spring进行查询时，出现org.springframework.dao.IncorrectResultSizeDataAccessException错误
        1. 原因：查询结果的数量有多个，但是方法返回值的类型时单个的对象或者基本数据类型
        1. 解决方法：使用数组等容器，解决实际查询返回值的数量与方法返回值的数量不匹配的问题
	
1. ### 实用的库和方法
	1. #### 搜索
			1. apoc.search.node:一个函数，可以使用关键词查找节点，可以设置模糊检索
			2. apoc.trigger:可以用来实现触发器的功能
			3. apoc.node:可以查到节点的几乎所有相关信息
			4. apoc.agg.graph(path):返回路径上的所有节点和关系（DISTINCT） function