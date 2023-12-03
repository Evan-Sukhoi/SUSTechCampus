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

### 12.4 com.alibaba.fastjson.JSONException: safeMode not support autoType :...
问题原因：2017年3月15日，fastjson官方发布安全升级公告，该公告介绍fastjson在1.2.24及之前的版本存在代码执行漏洞，当恶意攻击者提交一个精心构造的序列化数据到服务端时，由于fastjson在反序列化时存在漏洞，可导致远程任意代码执行。 自1.2.25及之后的版本，禁用了部分autotype的功能，也就是”@type”这种指定类型的功能会被限制在一定范围内使用。而由于反序列化对象时，需要检查是否开启了autotype。所以如果反序列化检查时，autotype没有开启，就会报错。

解决方法：
高版本（2.*.*）参考[官方方法]{https://github.com/alibaba/fastjson/wiki/enable_autotype}没用。
但是[这篇文章]{https://huaweidevelopers.csdn.net/648c341010821a64020deae0.html}似乎可以解决，但囿于时间紧迫，无力探索，**遂降级到1.2.83及以下。**

经验：Maven使用的库似乎不一定是xml文件里面有的。所以在降级包时，如果无法解决问题，**一定要进入library里面看看有没有高版本的包没删干净仍在使用**。


 
