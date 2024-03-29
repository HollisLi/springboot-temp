# springboot-temp

#### 项目介绍

> 基于 SpringBoot3.2.3 的单体模板项目

#### 软件架构

- JDK 版本： 21
- Spring Boot 版本： 3.2.3
- 鉴权： Spring Security + jjwt
- 数据库： MySQL
- 数据库连接池： Druid 1.2.18
- ORM 框架： MyBatis-Plus 3.5.5
- 日志框架： Log4j2
- 三方工具
    - poi: 5.2.5
    - jjwt: 0.9.1
    - easyexcel: 3.3.3
    - javax.mail: 1.6.2
    - hutool-all: 5.8.27
    - commons-io: 2.15.1

#### 项目目录结构

``` text
|-- src
|   |-- main
|       |-- java
|       |   |-- com.temp
|       |       |-- annotation  自定义注解
|       |       |-- aspect  日志切面
|       |       |-- config  配置类
|       |       |-- constants  常量类
|       |       |-- context  上下文
|       |       |-- controller  外部访问接口
|       |       |-- domain  实体类
|       |           |-- dto  数据传输对象
|       |           |-- vo  视图对象
|       |       |-- entity  实体类
|       |       |-- enums  枚举类
|       |       |-- exception  异常类
|       |       |-- handler  全局统一处理器
|       |       |-- mapper  数据访问层
|       |       |-- security  安全认证
|       |       |-- service  业务逻辑层
|       |       |-- task  定时任务
|       |       |-- util  工具类
|       |-- resources
|           |-- mapper MyBatis-Plus Mapper
|           |-- application.yml 项目配置文件
|           |-- log4j2.xml 日志配置文件
|-- .gitignore git忽略文件
|-- pom.xml 项目依赖
```

#### 使用说明

1. 请保证项目编码为 UTF-8
2. Java 编译版本为 21
3. Maven 版本无限制

#### 日志框架配置

> 日志框架采用Log4j2，配置文件为 log4j2.xml。
