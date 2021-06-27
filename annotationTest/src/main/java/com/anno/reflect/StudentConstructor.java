package com.anno.reflect;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: Student1.java
 * @Description: 你可以发现 所有构造函数均可以被获取！ 解析反射构造函数过程
 * @Package com.anno
 * @Time: 2021-06-27 22:17
 */
public class StudentConstructor {
    //---------------构造方法-------------------
    //（默认的构造方法） 一般是Protected
    StudentConstructor(String str){
        System.out.println("(默认)的构造方法 s = " + str);
    }

    //无参构造方法
    public StudentConstructor(){
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public StudentConstructor(char name){
        System.out.println("调用了公有、单参构造方法, 姓名：" + name);
    }

    //有多个参数的构造方法 2个以上
    public StudentConstructor(String name , int age){
        System.out.println("调用了公有、双参构造方法，姓名："+name+"年龄："+ age);//这的执行效率有问题，以后解决。
    }

    //受保护的构造方法  类似于默认
    protected StudentConstructor(boolean n){
        System.out.println("调用了保护、单参构造方法，受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private StudentConstructor(int age){
        System.out.println("调用了私有、单参构造方法,    年龄："+ age);
    }

}

/*
 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
 *
 * 1.获取构造方法：
 *      1).批量的方法：
 *          public Constructor[] getConstructors()：所有"公有的"构造方法
            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)

 *      2).获取单个的方法，并调用：
 *          public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
 *          public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
 *
 *          调用构造方法：
 *          Constructor-->newInstance(Object... initargs)
 */