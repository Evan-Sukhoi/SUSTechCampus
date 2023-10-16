# SUSTech Campus 开发日志

# 后端

### 10.14 MyBatis Plus报错 Property ‘sqlSessionFactory‘ or ‘sqlSessionTemplate‘ are required

解决：

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.3</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

http://t.csdnimg.cn/hmkd6



### 10.15 MyBatis-Plus-Join 报错Invalid bound statement (not found) :xxx selectJoinList 

问题原因：spring boot版本与MyBatis-Plus-join版本不匹配。其中，
spring-boot-starter-parent: 3.1.4
mybatis-plus-join: 1.4.2

解决方案：
更新mybatis-plus-join库至最新版本1.4.6



 