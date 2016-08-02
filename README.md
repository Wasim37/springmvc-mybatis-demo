# springmvc-mybatis-demo
通用后台权限管理项目  

##项目简介
1、框架为SpringMVC + MyBatis + shiro + Maven  
2、数据库连接池为druid，druid具体使用见[链接](https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)    
3、项目配置了缓存redis和消息队列beanstalkd  
4、项目common文件夹包含功能实现常用的各种Util，比如文件上传下载、密码加密、邮件发送、各种数据转换等  
5、后台页面框架使用了自适应的bootstrap ace模板，模板官方地址：[http://wrapbootstrap.com/preview/WB0B30DGR](http://wrapbootstrap.com/preview/WB0B30DGR)  
6、项目已实现用户及权限模块，基本功能包括用户登录、用户注销、密码修改、密码重置、新增用户、新增角色、用户角色配置、角色功能配置等    
7、如果想新增模块进行功能扩展，可模仿function表已有数据添加数据，并进行权限配置  
8、对于新增的数据库表，可运行/src/main/java/com/tyxj/build目录下的MybatisBuilder.java文件，自动生成bean、mapper和xml  

##使用说明
1、将项目clone到本地，导入Eclipse，运行build install命令下载相关jar包  
2、根据build文件夹中的fndmanager.sql建立相关数据库    
3、解压build文件夹中的redisbin_x64.zip，运行Windows版的redis-server.exe程序  
4、启动Tomcat，运行项目，用户名密码为admin，admin  


