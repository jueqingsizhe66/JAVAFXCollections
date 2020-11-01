
# 项目使用说明

+ `javafx.base`是一个模块
+ `javafx.scene`也是一个模块,可以用dot连接
+ `javafx.graphics`也是一个模块，以此类推

# 目录

<!-- markdown-toc start - Don't edit this section. Run M-x markdown-toc-refresh-toc -->
**Table of Contents**

- [项目使用说明](#项目使用说明)
- [目录](#目录)
    - [技术路线配置](#技术路线配置)
    - [设计流程](#设计流程)
    - [编译命令](#编译命令)
    - [运行命令](#运行命令)
    - [打包命令：](#打包命令：)
    - [项目整体结构](#项目整体结构)
        - [項目的核心类](#項目的核心类)
            - [双向绑定的例子](#双向绑定的例子)
            - [高级双向绑定](#高级双向绑定)
            - [小结](#小结)
            - [绑定计算](#绑定计算)
                - [传统addListener例子](#传统addlistener例子)
                - [javafx Property binding计算](#javafx-property-binding计算)
            - [小结2](#小结2)
            - [javafx集合类型 单向和双向绑定](#javafx集合类型-单向和双向绑定)
            - [集合类型中的valueAt(重要)](#集合类型中的valueat重要)
                - [Label实时更新](#label实时更新)
            - [小结3](#小结3)
            - [自定义绑定](#自定义绑定)
        - [某类初始化](#某类初始化)
    - [社区文档](#社区文档)

<!-- markdown-toc end -->

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

Application类的作用，有点类似[ a little java ][7]讲述的访问者模式(叠套设计)，**所有的组件都实现了Observable接口**

``` java
(Application 
    (Stage 
        (Scene 
            (Layout 
                (Button 
                    (Combobox 
                        (Table ...)
                    )
                )
            )
        )
    )
)
```

### 項目的核心类

所有javafx控件都是[Bindings子类][10]
- `Bindings` javafx的特色核心功能，双向绑定
   规律1： 所有的绑定一般都是返回[Property类][9]，比如`StringPropery(t1.textProperty())`, `integerProperty`,`DoubleProperty(an.widhthProperty(), slider.valueProperty()...)` Very Important!  然后Property类可以添加监听！一般逻辑！
- `ValueAt`函数
- `FXCollections` (回忆)
- `Observable`接口 (回忆)

#### 双向绑定的例子

``` java
  TextField t1 = new TextField();
  TextField t2 = new TextField();
  t1.textProperty().addListener(new ChangeListener<String>()){
    @override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            t2.setText(newValue);
        }
  }

  t2.textProperty().addListener(new ChangeListener<String>()){
    @override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            t1.setText(newValue);
        }
  }
  
  Button bu= newButton("Translate");
  Slider slider = new Slider();
  slider.setPrefWidth(500);
  slider.valueProperty().addListener(new ChangeListener<Numeber>(){
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
    {
        bu.setTranslateX(newValue.doubleValue());
    }
  });
  
  
  bu.translateXProperty().bind(slider.valueProperty()); // 既然bu的XProperty已经*单向绑定*了，那么就无法继续设置值了
  //他的值由slider.valueProperty()值给定! 即左端无法自我改变了，右端改变，左端才跟着改变!
```
t1,t2实现了双向绑定

那么如何使用javafx内置的双向绑定的方式？

``` java

//双向绑定
t1.textProperty().bindBidirectional(t2.textProperty());

//单向绑定
t1.textProperty().bind(t2.textProperty()); //此时t1无法改变，只有t2改变才能改变!
```

#### 高级双向绑定

``` java

//双向绑定
t1.textProperty().bindBidirectional(t2.textProperty(), new StringConverter<String>(){
    @Override
    public String toString(String string){
        if(string.contains("6")){
            String str= string.replace("6","陆")
            return str;
        }
        return string;
    }

    @Override
    public String fromString(String string){
        if(string.contains("5")){
            String str= string.replace("5","五")
            return str;
        }
        return string;
    }
});
```

- fromString实现t1输入包含5，那么经过绑定后(t1->fromString->t2)，t2的值就变成了五, 第一条链
- toString 实现t2输入6，那么经过绑定后(t2->toString->t1),t1的值就变成了陆,第二条链

#### 小结

bind or bidirection(更方便，更简短，定制化能力更强)替换掉了addListener(原始方式,自定义能力更强)的功能。


#### 绑定计算

使用两种方式实现双向绑定：传统AddListener() 和javafx双向绑定

##### 传统addListener例子

``` java
    int c= 0;
    int a= 3;
    int b= 6;
    c= a+b;
    System.out.prinltn(c);  //输出c =9
    
    a=4;
    
    System.out.prinltn(c);  //输出c 依然=9
    
    c=a+b;
    System.out.prinltn(c);  //输出c=10

```
如果是javafx凡是?

##### javafx Property binding计算

``` java

    SimpleIntegerProperty a = new SimpleIntegerProperty(1);
    SimpleIntegerProperty b = new SimpleIntegerProperty(3);
    IntegerBinding ib = a.add(6);
    NumberBinding nb = a.add(b);//float int double 的取值方式都是floatValue IntValue doubleValue
    System.out.println(ib.get());
    System.out.println(a.get());
    a.set(10);
    System.out.println(a.get());
    System.out.println(ib.get());
    System.out.println(nb.intValue());
    
    
```

#### 小结2

依次类推，减法、乘法等, 同理还有绑定判断(关系运算符:大于等于 返回都是BooleanBinding!，可用于[ 图片切换 ][16])
查看[ NumberExpression ][11]知道所有的运算
规律二: 在javafx中所有的Expression结尾的后缀都是运算相关的接口，如果某类实现该接口，代表它具有运算功能
javafx中[ BooleanBinding ][12]都会对应[ BooleanExpression ][13], 同理[ LongBinding ][14]都会有一个[ LongExpression ][15]与之对应。

规律三：一般Expression类型都是只读，不能写，可暴露给用户，比如IntegerExpression

推论： 也就是说比如au.widthProperty()他实现了NumberExpression那么他就可以执行divide函数，也就是说所有的组件获得他的
值属性之后都可以进行数值运算，并且返回的结果依然是对应的Expression类型，具备链式计算能力。
<2020-11-01 22:15>

#### javafx集合类型 单向和双向绑定

+ List   SimpleListProperty<String>
+ Set  SimpleSetProperty<String>
+ Map SimpleMapProperty<String,String>

初始值如何给集合？

`observableList<String> obs = FXCollections.observableArrayList();`
obst就是提供给List的初始值

``` java

observableList<String> obs1 = FXCollections.observableArrayList();
SimpleListProperty<String> list1 = new SimpleListProperty<String>(obs1);

list1.add("A");
list1.add("B");



observableList<String> obs2 = FXCollections.observableArrayList();
SimpleListProperty<String> list2 = new SimpleListProperty<String>(obs2);

list2.add("C");
list2.add("D");


list1.getValue();

list1.bind(list2); //集合单向绑定和属性添加有所不同，单向绑定和双向绑定一样！ list1依然可以添加元素！！！ 很不一样

list1.sort((item1,item2) -> item2.compareTo(item1)); // 此时只有obs2变化，那么对应list2的getValue()也变化

```

规律4： 集合的单向和双向绑定功能一致,因为此时list1 list2都维护obs2列表!  都指向obs2 , 如果打印obs1  obs2是有所不同的
推论4： SimpleListProperty只是指向ObservableList的指针，指针是墙头草,是可变的，就好像贝多芬《命运》交响曲，总是起伏跌宕，
充满故事性和戏剧性；命运是变化的，指针也是变化的；但同时因为不确定、变化，具有挑战性，颇受一些激情人士喜欢，他们喜欢变化、
喜欢突破。 所以list1、list2一开始分别指向obs1 ,obs2, 但是因为bind之后，list1指向obs2,要把数据链条搞清楚。

规律5：集合的bindContent不会改变维护的obs列表，但是如何list2内容发生改变也会同步修改到obs1!

#### 集合类型中的valueAt(重要)


运用场景： 可以把集合中的某个元素绑定到某个控件属性值! 实时更新几何数据

```

observableList<String> obs1 = FXCollections.observableArrayList();
SimpleListProperty<String> list1 = new SimpleListProperty<String>(obs1);

list1.add("A");
list1.add("B");
list1.add("C");

ObjectBinding<String> objbind = list.valueAt(0);

System.out.println(objbind.get()); // 输出A

list1.set(0,"F");

System.out.println(objbind.get()); //输出F


SimpleIntegerProperty index = new SimpleIntegerProperty(2);
ObjectBinding<String> objbind2 = list.valueAt(index);

System.out.println(objbind2.get()); // 输出A
list.set(2,"T");
System.out.println(objbind2.get()); // 输出T
index.set(1); 
System.out.println(objbind2.get()); // 输出B


```

##### Label实时更新

```

observableList<String> obs1 = FXCollections.observableArrayList();
SimpleListProperty<String> list1 = new SimpleListProperty<String>(obs1);

list1.add("A");
list1.add("B");
list1.add("C");

for(int I = 0 ; I< list1.size(); I++)
{
    Lable lable = new Lable();
    lable.setFont(new Font(30));

    //lable.setText(list1.get(I)); // 实时比较麻烦，添加监听0
    //通过集合的valueAt实现实时更新
    ObjectBinding<String> objbind = list1.valueAt(I);
    lable.textProperty().bind(objbind); // 实时绑定更新了
    // 这样只要list1的值改变了， 那么标签的信息也不断改变（设置相应控件即可！）
    vbox.getChildren().add(lable);
}

TextField t1= new TextField("输入");
TextField t2= new TextField("输出");
t2.textProperty().addListener(new ChangeListener<String>(){
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
        if(t1.getText().equals("")){
            return;
        }
        try
        {
            int index = Integer.parseInt(t1.getText());
            String value= t2.getText();
            list.set(index,value);
        }catch(Exception e)
        {
            System.out.println("异常");
        }
    }
})
```

类似于[ Bindings.createStringBinding ][19]

#### 小结3

绑定有Simple*Property系列，包含Integer等单属性和List等集合属性两种方式。

![JAVAFXCollections][17]

![JavaFX两种主要数据类型][18]

由于有了javafx的bingding类型，使用SImpleProperty系列数据类型替换掉基本数据类型，可以实现快速修改对象功能。

引申出TableView的字段属性的原始实现，结合`Binding.selectString`

``` java

Student student = new Student("A");
SimpleObjectProperty<Student> stu = new SimpleObjectProperty<Student>(student);

StringBinding strValue = Bindings.selectString(stu,"stuname","name");
System.out.prinltn(strvalue.get());// 打印A

student.setName("B");
System.out.prinltn(strvalue.get());// 打印B



```

为什么？ 请看Student类的功能定义

``` java

public class Student{
    private Name name = new Name();
    private SimpleObjectProperty<Name> stuname = new SimpleObjectProperty<Name>();
    
    // 固定写法  stunameProperty
    public SimpleObjectProperty<Name> stunameProperty(){
        System.out.println("Student");
        return stuname;
    }
    
    public Student(String name){
        this.name.setName(name);
        this.stuname.set(this.name);
    }
    
    public void setName(String name){
        this.name.setName(name);
//        this.stuname.set(this.name); // 构造函数已设置好了
    }
}

public class Name
{
   private SimpleStringProperty name = new SimpleStringProperty();
   // 固定形式  name+Propery
   public SimpleStringProperty nameProperty(){
    System.out.println("Name);
    return name;
   }
   public void setName(String name){
    this.name.set(name);
   }
}
```

TableView的字段实现就是基于SimpleProperty系列的数据结构。

#### 自定义绑定

有时候为了在一次计算中，把参数提供，但是算法封装起来，不想通过把BindingExpression的计算过程体现在  一行代码计算中，
而是封装在一个类中，那么就可以使用自定义绑定

``` java
package org.data.Entity;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;

public class BindingMyIntegerCustomize extends IntegerBinding {

    private SimpleIntegerProperty sip = new SimpleIntegerProperty();
    public BindingMyIntegerCustomize(int num){
//        sip = new SimpleIntegerProperty(num); //错误写法
        this.bind(sip); // 没有bind则不会实时更新
        sip.set(num);

        // IntegerBinding provides a simple invalidation-scheme. An extending class can register dependencies by calling bind(Observable...).
        // this.bind(sip);  // 和创建新对象的写法类似 ? 不一样 必须得用bind写法
        // sip.set(num);

    }
    public void setValue(int num){
        sip.set(num);
    }
    @Override
    protected int computeValue() {
        // 可以自定义各种计算， 在Bindings工具类提供了很多加 减 乘除 但没有计算面积等
        return sip.get()*100*3;
    }
}


```

流程为: `MyIntegerCustomer`-> `SIP` -> `SetName` -> `computeValue` -> `show`

自定义绑定其实就是拓展Expression算法集合。



### 某类初始化

FXML方式实现`Initializable`接口即可! 参考`BindTheoryTest.java`文件 

FXML和setOnAction()有所区别


## 社区文档 

+ [javafx官方][3]
+ [javafx中文社区][2]
+ [API文档][8]  **很重要!**
+ [JAVAFX中文视频教程 bindings bilibili][5]
+ [学生管理系统][4]
+ [易佰教程][5]




----------------------------------------------

[1]:https://github.com/jueqingsizhe66/whyJavaModule 
[2]:https://openjfx.cn 
[3]:https://openjfx.io/openjfx-docs/#maven 
[4]:https://github.com/commonyzb/StudentManagement/blob/master/database/studentdb.sql 
[5]:https://space.bilibili.com/5096022/channel/detail?cid=16953 
[6]: https://www.yiibai.com/javafx
[7]: https://github.com/jueqingsizhe66/ALittleJava
[8]: https://openjfx.cn/javadoc/15/
[9]: https://openjfx.cn/javadoc/15/javafx.base/javafx/beans/property/package-summary.html
[10]: https://openjfx.cn/javadoc/15/javafx.base/javafx/beans/binding/package-summary.html
[11]: https://openjfx.cn/javadoc/15/javafx.base/javafx/beans/binding/NumberExpression.html
[12]: https://openjfx.cn/javadoc/15/javafx.base/javafx/beans/binding/BooleanBinding.html
[13]: https://openjfx.cn/javadoc/15/javafx.base/javafx/beans/binding/BooleanExpression.html
[14]: https://openjfx.cn/javadoc/15/javafx.base/javafx/beans/binding/LongBinding.html
[15]: https://openjfx.cn/javadoc/15/javafx.base/javafx/beans/binding/LongExpression.html
[16]: https://www.bilibili.com/video/BV1Kt411e7Bw/?spm_id_from=333.788.videocard.0
[17]: https://github.com/jueqingsizhe66/JAVAFXCollections/blob/develop/image/FXCollections.png
[18]: https://github.com/jueqingsizhe66/JAVAFXCollections/blob/develop/image/JavafxTwoTypes.png
[19]: https://www.bilibili.com/video/BV16t411z7jg/?spm_id_from=333.788.videocard.13
