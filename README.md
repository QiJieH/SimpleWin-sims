# SimpleWin-sims
基于Java Swing 现代win风格的学生信息管理系统，使用MySQL作为项目数据库，含部署文档。--课程设计

## 项目描述
- JavaSwing
- MySQL
- 登录 注销登录
- 角色划分
- 密码文本加密
- 日志记录
- 增删改查
- 高级查询
- 分页显示

## 项目预览
**主页面**
![](https://gitee.com/QiJieH/blog-image-bed/raw/master/20201010195731.png)

**登录窗口**
![](https://gitee.com/QiJieH/blog-image-bed/raw/master/20201010202152.png)

**顶部菜单**
![](https://gitee.com/QiJieH/blog-image-bed/raw/master/20201010202402.png)

**右键菜单**
![](https://gitee.com/QiJieH/blog-image-bed/raw/master/20201010202442.png)

**多选项卡操作**
![](https://gitee.com/QiJieH/blog-image-bed/raw/master/20201010202622.png)

**控制台高级查询**
![](https://gitee.com/QiJieH/blog-image-bed/raw/master/20201010205242.png)

## 部署教程

1. 数据库相关
本项目使用MySQL作为数据库，确保你已经正常安装数据库服务，你需要为本项目创建一个数据库用户，用户名和密码均为root。
或者你可以直接修改项目源码中的datamodel/DBConnection类的用户和密码替换为你已有的数据库角色。确保数据库读写权限。
```
// datamodel/DBConnection
private final static String user = "root";
private final static String pwd = "root";
```

2. 使用Eclipse打开本项目文件
> Eclipse版本：
> Eclipse IDE for Enterprise Java Developers
> Version: 2020-09 (4.17.0)
> 
**你可能需要自行向项目中导入JDBC mysql-connector-java-8.0.21**

3. 使用预留代码构造测试数据
在app/index类中注释免登录和登录的语句，仅保留构造测试数据代码，执行项目。
如果出错确保数据库相关设置正常。
```java
public static void main(String args[]) {
		/** 构造测试数据 */
		InitTestData.build();
		/** 免登录进入开发模式 */
		//MainView mv = new MainView(null);
		/** 登录程序 */
		//LoginView lv = new LoginView();
	}
```

4. 恢复登录视口，尝试登录
构造数据预设了管理员账户 admin admin，使用账号登录
```java
public static void main(String args[]) {
		/** 构造测试数据 */
		//InitTestData.build();
		/** 免登录进入开发模式 */
		//MainView mv = new MainView(null);
		/** 登录程序 */
		LoginView lv = new LoginView();
	}
```