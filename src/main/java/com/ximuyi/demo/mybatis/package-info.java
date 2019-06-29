package com.ximuyi.demo.mybatis;

/**
 1. MojoExecutionException: Column name pattern can not be NULL or empty
 数据库驱动参数增加:nullNamePatternMatchesAll=true

 2.Cannot obtain primary key information from the database, generated objects may be incomplete
 据库驱动参数增加:nullCatalogMeansCurrent=true

 3.运行时，http调用报错~
     org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.ximuyi.demo.mybatis.MybatisUserService.addUser
     at org.apache.ibatis.binding.MapperMethod$SqlCommand.<init>(MapperMethod.java:227) ~[mybatis-3.4.6.jar:3.4.6]
     at org.apache.ibatis.binding.MapperMethod.<init>(MapperMethod.java:49) ~[mybatis-3.4.6.jar:3.4.6]
     at org.apache.ibatis.binding.MapperProxy.cachedMapperMethod(MapperProxy.java:65) ~[mybatis-3.4.6.jar:3.4.6]
     at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:58) ~[mybatis-3.4.6.jar:3.4.6]
     at com.sun.proxy.$Proxy101.addUser(Unknown Source) ~[na:na]
     at com.ximuyi.demo.mybatis.MybatisUserController.addUser(MybatisUserController.java:35) ~[classes/:na]
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_25]
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_25]
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_25]
     at java.lang.reflect.Method.invoke(Method.java:483) ~[na:1.8.0_25]
     at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209) ~[spring-web-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136) ~[spring-web-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:891) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:797) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at javax.servlet.http.HttpServlet.service(HttpServlet.java:661) ~[tomcat-embed-core-8.5.34.jar:8.5.34]
     at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851) ~[spring-webmvc-5.0.9.RELEASE.jar:5.0.9.RELEASE]
     at javax.servlet.http.HttpServlet.service(HttpServlet.java:742) ~[tomcat-embed-core-8.5.34.jar:8.5.34]

    1.编译后的classes路径下并没有相应的XML文件，这是因为IDEA在编译的时候忽略掉了XML文件，一个解决方法是将所有的XML文件移动到Resource文件夹下，这样在编译的时候就会将XML文件一起.
    @MapperScan("com.ximuyi.demo.mybatis.mapper")扫描的路径必须是整一个~
    路径是com.ximuyi.demo.mybatis，断点时发现MybatisUserController::MybatisUserService类型被映射到mapper，并不不是一个Service
    2.或者 MybatisUserController::MybatisUserService 标明@Qualifier("mybatisUserServiceImpl")
    3.疑惑：Mapper接口都不需要增加注解 @Mapper的？？？？？
    //@MapperScan("com.ximuyi.demo.mybatis.mapper") 因为SpringbootApplication增加了这个
 **/