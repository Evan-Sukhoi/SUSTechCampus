# SUSTechCampus

数据库采用MySQL，同时搭配Redis作为缓存

服务器采用Nginx

后端管理使用mybatis（无需安装，在maven中执行即可）


## 后端数据持久化方法简介
### DAO（data access object）
此类文件位于package com.sustech.campus.database.dao

每个文件实现了一个DAO接口，该接口继承自MPJBaseMapper<City>，这是一个泛型接口，其使用了MyBatis Plus框架。

MyBatis Plus是MyBatis的一个增强工具，用于简化MyBatis的操作。MPJBaseMapper 是MyBatis Plus提供的基础Mapper接口之一，它提供了常用的数据库操作方法，如插入、更新、删除和查询等，可以通过泛型来指定操作的实体类型（在这里是City）。

因此，这个DAO接口使用了MyBatis Plus框架用于与数据库进行交互，并且它将实体（PO中）与数据库中的表进行了映射。这意味着可以使用这个接口来执行与城市表相关的数据库操作，而不必手动编写SQL查询和更新语句。 MyBatis Plus会帮助生成和执行这些SQL语句。

### PO（persistent object）

此类文件位于package com.sustech.campus.database.po

每个文件都实现了一个PO（Persistence Object）类，也可以称为实体类。在持久层开发中，PO类通常用于表示数据库中的表结构，每个PO类的字段对应数据库表中的列。

这些PO类使用了一些Java技术和框架：

Lombok注解： 自动生成常见的Java类方法，如Getter、Setter、构造函数等。这些注解包括@Data、@AllArgsConstructor、@NoArgsConstructor 和 @Builder。例如，@Data 注解自动生成了类的Getter和Setter方法，@AllArgsConstructor 自动生成了一个包含所有属性的全参构造函数，@NoArgsConstructor 自动生成了一个无参构造函数，@Builder 自动生成了一个Builder模式的构建器。Lombok可以减少开发人员的样板代码编写工作，提高了代码的可读性。

MyBatis Plus注解： @TableId 和 @IdType.AUTO 是MyBatis Plus的注解。@TableId 用于标识实体类中的主键字段，@IdType.AUTO 表示主键的生成策略是自增长（通常用于自动递增的主键字段）。

字段定义： 比如一个City类，它包含了一些属性，如 id、name、provinceId 和 isProvincialCapital，这些属性对应了数据库表中的列。

## 接下来后端需要做的工作

根据数据库的设计，仿照学习链接，完成Dao和Po的填充。

学习链接：[https://github.com/QuanQuan-CHO/SUSTech-Regency](https://github.com/QuanQuan-CHO/SUSTech-Regency)
