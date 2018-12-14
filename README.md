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
