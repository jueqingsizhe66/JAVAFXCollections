# 项目使用说明

+ `javafx.base`是一个模块
+ `javafx.scene`也是一个模块,可以用dot连接
+ `javafx.graphics`也是一个模块，以此类推

## 技术路线配置
java JDK 官网13.02

Javafx 14(jdk 和fx已分离)

+ 当前Javafx版本 16-ea+3
+ Maven3.3.9
+ FXML
+ CSS

## 设计流程

+ scene中绘制,生成fxml文件,并定义相应组件名字
+ 建立对应控制类，引用组件ID
+ 实现所需功能，在javafx运行周期内运行功能

## 编译命令
```maven
mvn javafx:compile
```
## 运行命令
```maven
mvn javafx:run
```
## 打包命令：

打包後路徑： 
`E:\codeRoom\java\testJAVAFX4\target\`

```shell script
java  --module-path D:\ManyHomes\JAVA\jdk-13.0.2\javafx-sdk-11.0.2\lib\  --add-modules=javafx.controls,javafx.fxml -jar hellofx.jar
```

关于`module-path`的说明参考[WhyJavaModule][1]
可了解
+ `requires`
+ `exports`
+ `opens`

javafX的sdk得下载下来才能使用

## 项目整体结构

项目启动Controller为App.java, 相对路径为`src/main/java/org/openjfx/App.java`

每一个界面都包含一个fxml文件和controller类的java函数
比如primary.fxml(`src/main/resources/org/openjfx/primary.fxml`)对应的PrimaryControler可在 fxml设计界面SceenBuilder的hierarchy指定Controller类，进行绑定


### 項目的核心類

所有javafx控件都是`Bindings`子類
- `Bindings`
- `ValueAt`函數
- `FXCollections` (回憶)
- `Observable`接口 (回憶)

### 某類初始化

FXML方式實現`Initializable`接口即可! 參考`BindTheoryTest.java`文件 

## 社区文档 

+ [javafx官方][3]
+ [javafx中文社區][2]
+ [JAVAFX中文视频教程 bindings bilibili][5]
+ [学生管理系统][4]






----------------------------------------------

[1]:https://github.com/jueqingsizhe66/whyJavaModule 
[2]:https://openjfx.cn 
[3]:https://openjfx.io/openjfx-docs/#maven 
[4]:https://github.com/commonyzb/StudentManagement/blob/master/database/studentdb.sql 
[5]:https://space.bilibili.com/5096022/channel/detail?cid=16953 