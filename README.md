# system

### 框架说明
- spring-boot-2.0.1.RELEASE
- spring-security-5.0.4.RELEASE
- mysql-5.1.46
- mybatis-3.4.6
- tkmybatis-3.4.9
- fastjson-1.2.46
- pagehulp-5.1.1

### 结构说明
```
com.sys.system
   |
   └────────base(主要的接口或者类)
   |
   └────────config(配置信息)
   |
   └────────exception(自定义的异常)
   |
   └────────model(模块)
         |
         └────────menu(菜单,里面包含server和rest)
   |     |
   |     └────────user(用户模块,里面包含server和rest)  
   |
   └────────security(权限)
   |
   └────────util(工具)
         |
         └────────http(http相关的工具类)
```

### 说明
这个项目集成了最新版本的security oauth2框架,暂时提供密码授权模式,采用数据库存储形式保存,
还提供security oauth2自定义响应参数的说明,security oauth2使用的原生的表,当然如果你想自定义,
注释上有说明,不想自定义又不知道表和类怎么对应表的,没关系,这里提供类到表对应说明,同时标注每一个
方法什么意思,在DAO层集成了tkmybatis和pagehelp的最新版,从此分页
和常用的sql不在手写,同时mybatis采用注解的形式,没有mapper.xml文件,数据库连接采用druid,同时配置了sql监控
,采用lombok形式,从此打日志,get和set方法不用idea自动生成,只用一个注解即可完成优雅编程.如果你是小白,那也不用担心
这里的方法和类都提供注释,帮助你理解,最关键,这个项目打包即用,不会产生错误或异常,采用没有入侵性的项目名称,适合任何项目模板,
同时这个项目目录结构方便拆分成微服务,适合中小项目,后期方便扩展