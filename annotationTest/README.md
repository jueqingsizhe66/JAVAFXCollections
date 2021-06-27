
围绕java核心技术注解，展开一系列的调研学习
参考[注解视频][1]

## 1. 为什么要学习注解？

数组是java的基础知识点。
注解和反射都是java核心的知识点。
结合反射技术、IO技术、集合技术、多线程技术、网络编程、JUC编程

进一步结合当前主模块的javafx bindings技术！双向绑定！

### 注解的好处：
  - [省去大量beam xml配置][2]

### 什么是反射？
在了解什么是注解前，而得清楚知道反射的定义。
反射是框架设计的灵魂
JAVA反射机制是在运行状态中，
   1. 对于任意一个类，都能够知道这个类的所有属性和方法；
   2. 对于任意一个对象，都能够调用它的任意一个方法和属性； 
   3. 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。

要想解剖一个类,必须先要获取到该类的字节码文件对象。
而解剖使用的就是Class类中的方法.所以先要获取到每一个字节码文件对应的Class
类型的对象. 以上的总结就是什么是反射

反射就是把java类中的各种成分映射成一个个的Java对象(一切皆对象！)
反射的原理在于加载class对象机制，简称类加载机制。

注意：在运行期间，一个类，只有一个Class对象产生。
三种方式常用第三种，第一种对象都有了还要反射干什么。
第二种需要导入类的包，依赖太强，不导包就抛编译错误。
一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法

从上面我们知道一个全路径类在内存只有一个Class对象产生

## 2. 什么是注解？

注解是...的技术？
注解是一项用于简化代码的技术，使得代码更加简洁友好

Java 注解用于为 Java 代码提供元数据。
作为元数据，注解不直接影响你的代码执行，但也有一些类型的注解实际上可以用于这一目的。
Java 注解是从 Java5 开始添加到 Java 的。
这是大多数网站上对于 Java 注解，解释确实正确，但是说实在话，
我第一次学习的时候，头脑一片空白。这什么跟什么啊？听了像没有听一样。
因为概念太过于抽象，所以初学者实在是比较吃力才能够理解，然后随着自己开发过程中
不断地强化练习，才会慢慢对它形成正确的认识。

从上面这段话我们得到3点注解知识：
1. 注解提供给代码元数据
2. 注解不影响程序执行
3. 注解从java5就一直存在

## 3. 注解包含哪几个部分?

### 1. 注解的定义

注解通过 @interface 关键字进行定义。

```java
public @interface TestAnnotation {

}
```
它的形式跟接口很类似，不过前面多了一个 @ 符号。上面的代码就创建了一个名字为
TestAnnotaion 的注解。
你可以简单理解为创建了一张名字为 TestAnnotation 的标签。

### 2. 注解的注册
### 3. 注解的实现
### 3.A 元注解
元注解是可以注解到注解上的注解，或者说元注解是一种基本注解，
但是它能够应用到其它的注解上面。
元注解也是一张标签，但是它是一张特殊的标签，它的作用和目的就是给其他普通的标签
进行解释说明的。
元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种

#### 1. @Retention
Retention 的英文意为保留期的意思。当 @Retention 应用到一个注解上的时候，
它解释说明了这个注解的的存活时间。

它的取值如下：

   1. RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
   2. RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
   3. RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。

我们可以这样的方式来加深理解，@Retention 去给一张标签解释的时候，它指定了这张标签张贴的时间。@Retention 相当于给一张标签上面盖了一张时间戳，时间戳指明了标签张贴的时间周期。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    
}
```

上面的代码中，我们指定 TestAnnotation 可以在程序运行周期被获取到，
因此它的生命周期非常的长。
#### 2. @Documented
顾名思义，这个元注解肯定是和文档有关。
它的作用是能够将注解中的元素包含到 Javadoc 中去。
#### 3. @Target
Target 是目标的意思，@Target 指定了注解运用的地方。

你可以这样理解，当一个注解被 @Target 注解时，这个注解就被限定了运用的场景。

类比到标签，原本标签是你想张贴到哪个地方就到哪个地方，但是因为 @Target 的存在，
它张贴的地方就非常具体了，比如只能张贴到方法上、类上、方法参数上等等。
@Target 有下面的取值: 

    1. ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
    2. ElementType.CONSTRUCTOR 可以给构造方法进行注解
    3. ElementType.FIELD 可以给属性进行注解
    4. ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
    5. ElementType.METHOD 可以给方法进行注解
    6. ElementType.PACKAGE 可以给一个包进行注解
    7. ElementType.PARAMETER 可以给一个方法内的参数进行注解
    8. ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举

#### 4. @Inherited

可以备继承的注解，子类具有父类注解。(爷爷是富豪，子孙三代都是富豪)

```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Test {}
@Test
public class A {}
/*B也有Test注解*/
public class B extends A {}

```
#### 5. @Repeatable

其他重用标签： 
- @Deprecated标记过时
- @Override标记重载
- @SuppressWarnings
- @SafeVarargs
- @FunctionalInterface  (只能有一个函数)
  函数式接口注解，这个是 Java 1.8 版本引入的新特性。函数式编程很火，
  所以 Java 8 也及时添加了这个特性。
### 3.B 注解的属性（注解特别的地方）
注解的属性也叫做成员变量。注解只有成员变量，没有方法。
注解的成员变量在注解的定义中以“无形参的方法”形式来声明，
其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    int id();
    String msg();
}
```
上面代码定义了 TestAnnotation 这个注解中拥有 id 和 msg 两个属性。
在使用的时候，我们应该给它们进行赋值。

赋值的方式是在注解的括号内以 value=”” 形式，多个属性之前用 ，隔开。

```java

@TestAnnotation(id=3,msg="hello annotation")
public class Test {
}
```

注解中属性可以有默认值，默认值需要用 default 关键值指定。比如：

```java

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
   public int id() default -1;
   public String msg() default "Hi";
}
```

另外，还有一种情况。如果一个注解内仅仅只有一个名字为 value 的属性时，应用这个注解时可以直接接属性值填写到括号内。

```java
public @interface Check {
   String value();
}
```
上面代码中，Check 这个注解只有 value 这个属性。所以可以这样应用。
```java
@Check("hi")
int a;
```
这和下面的效果是一样的
```java
@Check(value="hi")
int a;
```

最后，还需要注意的一种情况是一个注解没有任何属性。比如

```java
public @interface Perform {}
```

那么在应用这个注解的时候，括号都可以省略。
```java
@Perform
public void testMethod(){}
```
### 4. 注解的应用

   我通过用标签来比作注解，前面的内容是讲怎么写注解，然后贴到哪个地方去，
   而现在我们要做的工作就是检阅这些标签内容。 
   形象的比喻就是你把这些注解标签在合适的时候撕下来，然后检阅上面的内容信息。
   注解通过反射获取。首先可以通过 Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解

```java
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {}
```

然后通过 getAnnotation() 方法来获取 Annotation 对象。

```java
public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {}
```
或者是 getAnnotations() 方法。

```java
public Annotation[] getAnnotations() {}
```

前一种方法返回指定类型的注解，后一种方法返回注解到这个元素上的所有注解。
上面创建了一个注解，那么注解的的使用方法是什么呢。

```java

@TestAnnotation
public class Test {
}
```

创建一个类 Test,然后在类定义的地方加上 @TestAnnotation 就可以用
TestAnnotation 注解这个类了。
你可以简单理解为将 TestAnnotation 这张标签贴到 Test 这个类上面。
不过，要想注解能够正常工作，还需要介绍一下一个新的概念那就是元注解。
## 4. 注解怎么样？

1. 类注解
2. 字段注解
3. 双亲委派机制
   掌握双亲委派机制
   该机制是用来防止同名包、类与 jdk 中的相冲突。
   实际上加载类的时候，
    1. 首先通知 appLoader(AL)，看 appLoader 是否已经缓存，
    2. 如果AL没有的话，appLoader 又委派给他的父类加载器(extLoader,EL)询问，看他是不是能已经缓存加载,
    3. 如果EL没有的话，extLoader 又委派他的父类加载器(bootstrapLoader,BSL)询问，BootstrapLoader看是不是自己已缓存或者能加载的，有就加载
    4. 如果BSL没有的话，再返回 extLoader，extLoader 能加载就加载，
    5. 如果EL不能的话,再返回给 appLoader 加载，再返回的路中，谁能加载，加载的同时也加缓存里。
    6. 正是由于不停的找自己父级，所以才有 Parents 加载机制，翻译过来叫 双亲委派机制。

[类加载器加载过程][3]
## 5. 学习注解该注意什么？
现在，我们可以给自己答案了，注解有什么用？给谁用？给 编译器或者 APT 用的。

## 6. 注解新解

   我想到了一样东西————墨水，墨水可以挥发、可以有不同的颜色，用来解释注解正好。
   不过，我继续发散思维后，想到了一样东西能够更好地代替墨水，那就是印章。
   印章可以沾上不同的墨水或者印泥，可以定制印章的文字或者图案，如果愿意它也可以
   被戳到你任何想戳的物体表面。 但是，我再继续发散思维后，又想到一样东西能够更好
   地代替印章，那就是标签。标签是一张便利纸，标签上的内容可以自由定义。常见的如货架
   上的商品价格标签、图书馆中的书本编码标签、实验室中化学材料的名称类别标签等等。
   并且，往抽象地说，标签并不一定是一张纸，它可以是对人和事物的属性评价。也就是说，
   标签具备对于抽象事物的解释

1. 像墨水
2. 像印章
3. 像标签（不影响本质内容，只是一个标注 一个提醒 各种明星标签，网络新词都是一种标签，
   用于人身上的标签，用于事件的标签"918事件" "皖南事变" "512" “双11” "渣男"）
   渣男还是渣男，渣男的标签并不会影响某些人正常生活
   
标签为了解释事物，注解为了解释代码

[1]:https://www.bilibili.com/video/BV1p4411P7V3?from=search&seid=16112160230838977085 
[2]:https://blog.csdn.net/lishaoran369/article/details/74942407 
[3]:https://blog.csdn.net/u012585964/article/details/52011138?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-7.baidujs&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-7.baidujs 
