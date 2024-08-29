shopping
========

我的毕业设计_网上商城

## 项目启动步骤##

1. 使用 db/shopping.sql 中的 sql 语句创建数据库与数据库表
    > 把你的数据库的账号密码改成root/root 或者修改 ShopConfig.java 中的数据库连接账号和密码
2. 将项目导入 eclipse。

3. 打开 comfig包下的 ShopConfig 文件，右键单击该文件并选择 Debug As ---> Java Application。
   >其它启动项目的方式见 《JFinal手册》。除此之外，项目还可以与其它普通java web 项目一样使用 tomcat
   jetty 等 web server 来启动，启动方式与非jfinal项目完全一样。

4. 打开浏览器输入  localhost 即可查看运行效果

注意： 请确保您安装了 JavaSE 1.6 或更高版本，tomcat下运行项目需要先删除 jetty-server-xxx.jar，否则会有冲突

	我本来想整理下这个项目，但是自己运行后发现太丑了，见不得人，有谁想玩的话，发邮件给我


​	
## idea启动项目
- 本地安装mysql（驱动用的是mysql-connector-java-8.0.13.jar，最好检查你本地数据库的版本是兼容）
- 安装jdk6以上，文档用的是jdk8
- 安装idea，文档用的版本是 IDEA 2018.3
- 本地安装git

### 1 clone 项目
    git clone https://github.com/jphuang/shopping.git

### 2 打开idea


### 3 file->open 选择shopping所在的文件夹，点OK
我的是在（F:\code\shopping ） 这个文件夹

  ![image-20240829143742429](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829143742429.png)
#### 项目配置
![image-20240829143804836](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829143804836.png)
   #### project   
   ![image-20240829143857980](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829143857980.png)
   #### modules
 ![image-20240829143918353](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829143918353.png)
   #### paths
  ![image-20240829143947489](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829143947489.png)
   #### add web
![image-20240829144010601](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829144010601.png)
  #### web path
 ![image-20240829144030318](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829144030318.png)
   #### lib
 ![image-20240829144227778](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829144227778.png)
   #### artifacts
   ![image-20240829144245463](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829144245463.png)
### 启动界面
   ![image-20240829144314978](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829144314978.png)
### 首页界面
   ![image-20240829144336053](https://raw.githubusercontent.com/jphuang/blog/master/img/image-20240829144336053.png)
    
    
     启动地址是：http://127.0.0.1:8090  
     后台地址是： http://127.0.0.1:8090/admin    
     登录账号和密码是： admin/admin

