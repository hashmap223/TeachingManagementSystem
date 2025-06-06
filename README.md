## 运行环境
+ Java 1.8.0
+ MySQL 8.0.33
+ Tomcat 8.5.50

## 构建环境
+ maven 3.9.6

## 数据源
用于测试的原始数据在 `/resources/OriginalDataSource.sql`

以任何方式导入并在 `/resources/mysql.properties` 中修改连接信息以在你的本地数据库运行。

## 项目结构
``` shell
.
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── com
        │       └── ssmproject
        │           ├── controller [控制器]
        │           │   ├── AdminController.java
        │           │   ├── converter [数据格式转换 工具类]
        │           │   │   └── CustomDateConverter.java
        │           │   ├── LoginController.java
        │           │   ├── RestPasswordController.java
        │           │   ├── StudentController.java
        │           │   └── TeacherController.java
        │           ├── exception [异常处理]
        │           │   ├── CustomException.java
        │           │   └── CustomExceptionResolver.java
        │           ├── mapper [MyBatis Mapper]
        │           │   ├── CollegeMapper.java
        │           │   ├── CourseMapperCustom.java
        │           │   ├── CourseMapper.java
        │           │   ├── RoleMapper.java
        │           │   ├── SelectedcourseMapper.java
        │           │   ├── shortTelMapper.java
        │           │   ├── StudentMapperCustom.java
        │           │   ├── StudentMapper.java
        │           │   ├── TeacherMapperCustom.java
        │           │   ├── TeacherMapper.java
        │           │   ├── UserloginMapperCustom.java
        │           │   └── UserloginMapper.java
        │           ├── po [实体类]
        │           │   ├── CollegeCustom.java
        │           │   ├── CollegeExample.java
        │           │   ├── College.java
        │           │   ├── CourseCustom.java
        │           │   ├── CourseExample.java
        │           │   ├── Course.java
        │           │   ├── Message.java
        │           │   ├── PagingVO.java
        │           │   ├── RoleExample.java
        │           │   ├── Role.java
        │           │   ├── SelectedCourseCustom.java
        │           │   ├── SelectedcourseExample.java
        │           │   ├── Selectedcourse.java
        │           │   ├── ShortTel.java
        │           │   ├── StudentCustom.java
        │           │   ├── StudentExample.java
        │           │   ├── Student.java
        │           │   ├── TeacherCustom.java
        │           │   ├── TeacherExample.java
        │           │   ├── Teacher.java
        │           │   ├── UserloginCustom.java
        │           │   ├── UserloginExample.java
        │           │   └── Userlogin.java
        │           ├── realm [Shiro 安全校验类]
        │           │   └── LoginRealm.java
        │           └── service [Service 服务层]
        │               ├── CollegeService.java
        │               ├── CourseService.java
        │               ├── impl [服务接口实现]
        │               │   ├── CollegeServiceImpl.java
        │               │   ├── CourseServiceImpl.java
        │               │   ├── RoleServiceImpl.java
        │               │   ├── SelectedCourseServiceImpl.java
        │               │   ├── shortTelServiceImpl.java
        │               │   ├── StudentServiceImpl.java
        │               │   ├── TeacherServiceImpl.java
        │               │   └── UserloginServiceImpl.java
        │               ├── RoleService.java
        │               ├── SelectedCourseService.java
        │               ├── shortTelService.java
        │               ├── StudentService.java
        │               ├── TeacherService.java
        │               └── UserloginService.java
        ├── resources [配置资源目录]
        │   ├── log4j2.xml [Log4j2 日志配置]
        │   ├── log4j.properties_backup [Log4j 日志配置 (备份 & 已弃用)]
        │   ├── mybatis [MyBatis 资源目录]
        │   │   ├── mapper [SQL Mapper 配置文件目录]
        │   │   │   ├── CollegeMapper.xml
        │   │   │   ├── CourseMapperCustom.xml
        │   │   │   ├── CourseMapper.xml
        │   │   │   ├── RoleMapper.xml
        │   │   │   ├── SelectedcourseMapper.xml
        │   │   │   ├── shortTelMapper.xml
        │   │   │   ├── StudentMapperCustom.xml
        │   │   │   ├── StudentMapper.xml
        │   │   │   ├── TeacherMapperCustom.xml
        │   │   │   ├── TeacherMapper.xml
        │   │   │   ├── UserloginMapperCustom.xml
        │   │   │   └── UserloginMapper.xml
        │   │   └── mybatis.cfg.xml [MyBatis 配置文件]
        │   ├── mysql.properties [数据库连接信息]
        │   ├── OriginalDataSource.sql [数据库 SQL转储文件]
        │   └── spring [Spring 配置文件目录]
        │       ├── applicationContext-dao.xml [Dao 数据存储配置]
        │       ├── applicationContext-service.xml [Service 配置]
        │       ├── applicationContext-shiro.xml [Shiro 框架配置]
        │       ├── applicationContext-trsaction.xml [数据库事务配置]
        │       └── springmvc.xml [MVC 配置]
        └── webapp [Web前端资源目录]
            ├── css [CSS 样式文件]
            │   ├── bootstrap.css
            │   ├── bootstrap.min.css
            │   ├── bootstrap-theme.css
            │   └── bootstrap-theme.min.css
            ├── fonts [字体库]
            │   ├── glyphicons-halflings-regular.eot
            │   ├── glyphicons-halflings-regular.svg
            │   ├── glyphicons-halflings-regular.ttf
            │   ├── glyphicons-halflings-regular.woff
            │   └── glyphicons-halflings-regular.woff2
            ├── images [图片资源]
            │   └── a.jpg
            ├── js [JavaScript 脚本]
            │   ├── bootstrap.js
            │   ├── bootstrap.min.js
            │   ├── jquery-3.2.1.min.js
            │   └── npm.js
            ├── login.jsp [登录页面模板]
            └── WEB-INF [Web页面资源]
                ├── jsp
                │   ├── admin [Admin 模块页面]
                │   │   ├── addCourse.jsp
                │   │   ├── addStudent.jsp
                │   │   ├── addTeacher.jsp
                │   │   ├── editCourse.jsp
                │   │   ├── editStudent.jsp
                │   │   ├── editTeacher.jsp
                │   │   ├── menu.jsp
                │   │   ├── passwordRest.jsp
                │   │   ├── showCourse.jsp
                │   │   ├── showStudent.jsp
                │   │   ├── showTeacher.jsp
                │   │   ├── top.jsp
                │   │   └── userPasswordRest.jsp
                │   ├── error.jsp [错误页面]
                │   ├── student [Student 页面]
                │   │   ├── menu.jsp
                │   │   ├── overCourse.jsp
                │   │   ├── passwordRest.jsp
                │   │   ├── selectCourse.jsp
                │   │   ├── showCourse.jsp
                │   │   └── top.jsp
                │   ├── success.jsp [操作成功页面]
                │   ├── teacher [Teacher 页面]
                │   │   ├── mark.jsp
                │   │   ├── menu.jsp
                │   │   ├── passwordRest.jsp
                │   │   ├── showCourse.jsp
                │   │   ├── showGrade.jsp
                │   │   └── top.jsp
                │   ├── up.jsp
                │   └── upLoad.jsp
                └── web.xml [JavaWeb配置]

27 directories, 129 files
```
## 处理流程
前端页面请求由Controller各模块负责监听并处理

Service层各模块向Controller提供服务完成请求操作(该层各方法均有事务操作)

Dao-Mapper 向Service层各个服务提供数据操作方法

Shiro 框架负责权鉴功能防止越权操作
请求处理链：

Controller ==调用==> Service ==调用==> Mapper

Mapper:
   
`src/main/java/com/ssmproject/mapper` 中的Mapper类均在 `src/main/resources/mybatis/mapper`中对应一个同名XML映射文件

Java Mapper类中描述的数据库操作方法在Mapper XML文件中由具体SQL语句实现