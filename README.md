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

   ![maze](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/172184A575A543F586B94CFAC6DAF8E0/4576)
#### 项目配置
   ![2](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/516671BF6D5A4462A904CD8D49B2F028/4582)
   #### project   
   ![3](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/006DE2DDF89E4F4495F0D8D7D5433162/4586)
   #### modules
   ![4](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/EC72D4DE21844D46AD2C9B36478C443E/4588)
   #### paths
   ![5](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/9462E5C7AED74992B882A575F6954E74/4590)
   #### add web
   ![6](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/F0D8236929BE4385AC6C185E92B78260/4593)
  #### web path
   ![7](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/BF7410DAD6D747289AE985993396F5C9/4595)
   #### lib
   ![9](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/4E968447F9274E3ABE41113AC6DAD0E8/4597)
   #### artifacts
   ![10](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/93BC7D0BBD754781B7AC3A2E43547473/4600)
 
### 启动界面
    
   ![10](https://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/0F718902E72B47139E19155C609CFBA8/4603)
###    登录界面
   ![登录界面](http://note.youdao.com/yws/public/resource/c231396963b8e88f57bc0f455afdcc9b/xmlnote/52839CF6F01E471EBB95DA6E089A8003/4606)
    
    
     启动地址是：http://127.0.0.1:8090  
     后台地址是： http://127.0.0.1:8090/admin    
     登录账号和密码是： admin/admin
	
