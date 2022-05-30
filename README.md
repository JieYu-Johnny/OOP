# 基于MD5摘要算法的登陆系统加密

## 文件树

```
│  pom.xml //maven的配置目录，里面有一些依赖
│
├─.idea//不用管
│
├─src    //源文件目录
│  ├─main
│  │  ├─java//后端逻辑代码
│  │  │  │  login.java
│  │  │  │  loginSystem.java 
│  │  │  │  MD5Utils.java //MD5加密包
│  │  │  │
│  │  │  ├─mapper//实现映射文件的接口文件
│  │  │  │      UserMapper.java
│  │  │  │
│  │  │  └─pojo //这个不用管
│  │  │          User.java
│  │  │
│  │  ├─resources //mybatis的配置文件 也不用管
│  │  │  │  mybatis-config.xml
│  │  │  │
│  │  │  └─mapper //Mybatis的映射文件
│  │  │          UserMapper.xml
│  │  │
│  │  └─webapp
│  │      │  fail.html//这个没用
│  │      │  index.jsp //主界面的前端代码
│  │      │  success.html//这个没用
│  │      │
│  │      ├─login//主界面的CSS和js代码 也不用管
│  │      │  │  main.js
│  │      │  │  README.md
│  │      │  │  style.css
│  │      │  │  transition.html
│  │      │  │
│  │      │  ├─doc
│  │      │  │      html-struct.png
│  │      │  │      preview.jpg
│  │      │  │
│  │      │  └─img
│  │      │          log.svg
│  │      │          register.svg
│  │      │
│  │      ├─main_menu
│  │      │      main.jsp//登录进入后的菜单界面，没啥东西不用管
│  │      │
│  │      └─WEB-INF
│  │              web.xml//不用管
│  │
│  └─test//测试类 不用管
│      └─java
│              sqltest.java
```

## 说明

MD5的介绍就去知网、csdn查，代码都有注释，不会的就必应查一下，都是简单的使用而已，实在不懂得再来问，webapp文件夹里面的都是前端的代码 基本上都不用看，就看后端怎么实现的就行
