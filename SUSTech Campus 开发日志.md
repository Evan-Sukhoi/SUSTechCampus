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

### 12.4 鉴权时从Redis反序列化报错 com.alibaba.fastjson.JSONException: safeMode not support autoType :...
问题原因：2017年3月15日，fastjson官方发布安全升级公告，该公告介绍fastjson在1.2.24及之前的版本存在代码执行漏洞，当恶意攻击者提交一个精心构造的序列化数据到服务端时，由于fastjson在反序列化时存在漏洞，可导致远程任意代码执行。 自1.2.25及之后的版本，禁用了部分autotype的功能，也就是”@type”这种指定类型的功能会被限制在一定范围内使用。而由于反序列化对象时，需要检查是否开启了autotype。所以如果反序列化检查时，autotype没有开启，就会报错。

解决方法：
高版本（2.*.*）参考[官方方法](https://github.com/alibaba/fastjson/wiki/enable_autotype)没用。
但是[这篇文章](https://huaweidevelopers.csdn.net/648c341010821a64020deae0.html)似乎可以解决，但囿于时间紧迫，无力探索，**遂降级到1.2.83及以下。**

经验：Maven使用的库似乎不一定是xml文件里面有的。所以在降级包时，如果无法解决问题，**一定要进入library里面看看有没有高版本的包没删干净仍在使用**。

### 12.5 发送邮件报错 java.lang.NoSuchMethodError: 'void com.sun.mail.util.LineInputStream.<init>(java.io.InputStream, boolean)'
根本原因是javax.mail包冲突。但是，按照网上的说法[http://t.csdnimg.cn/x3fNT](http://t.csdnimg.cn/x3fNT)，在maven的xml的dependency里面添加
```
<exclusions>
    <exclusion>
        <groupId>javax.mail</groupId>
        <artifactId>mailapi</artifactId>
    </exclusion>
</exclusions>
```
并没有解决问题。
之后，参考[这篇文章](https://www.cnblogs.com/cyb-652356/p/11718424.html)的说法：“**这个类确实在eclipse中自带java EE 5 Libraries中没有，但是我引用了外面的包还是报错，具体原因还是加载jar包的先后顺序问题，项目先去引用自带jar包中的mail类，所以外部引用的jar包自带mail不会引用。所以会报错。**”，我发现了自己代码中的
```
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>7.0</version>
            <scope>compile</scope>
        </dependency>
```
这里出现了问题。把`<scope>compile</scope>`改成`<scope>provided</scope>`就解决了。
GPT给出的解释是：
> `provided`范围的依赖表示**编译和测试**时需要这个依赖，但在**运行时由容器（例如应用服务器）提供**。这意味着当你的应用部署到容器中时，容器会提供 Java EE API，所以你不需要在应用中打包这个依赖。典型的例子就是 Java EE 容器，比如 Tomcat、JBoss 等，它们已经包含了 Java EE API 的实现。
> `compile` 范围的依赖是默认的范围，表示这个依赖在**编译、测试和运行**时都会被使用。如果选择了 compile 范围，那么该依赖将会被打包到你的应用中，并在运行时需要这个依赖。这对于一些常见的类库和工具非常有用，因为它们可能会被你的应用代码直接调用。
> 一般来说，在开发 Java EE 应用时，你应该使用`provided`范围，因为 Java EE 容器通常会提供 Java EE API。这样做可以减小部署包的大小，并确保运行时使用的是容器提供的正确版本的 API

由此，可知是该版本的javaEE中的mail包版本与本地的其他包发生了冲突
