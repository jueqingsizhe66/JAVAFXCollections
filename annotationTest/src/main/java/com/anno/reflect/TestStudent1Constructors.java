package com.anno.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: TestStudent1Constructors.java
 * @Description: (用一句话描述该文件做什么 ?)
 * @Package com.anno
 * @Time: 2021-06-27 22:21
 */
public class TestStudent1Constructors {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1. 加载Class对象
        Class student1Cls= Class.forName("com.anno.reflect.StudentConstructor");

        //2. 获取所有共有构造方法
        System.out.println("<<<<<<<<<<<<<<<<<所有共有构造函数>>>>>>>>>>>>>>>>>>>");
        Constructor[] conArray= student1Cls.getConstructors();
        for (Constructor constructor : conArray) {
            System.out.println(constructor);
        }
        //3. 全部构造方法
        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        for (Constructor con1 : student1Cls.getDeclaredConstructors()) {
            System.out.println(con1);
        }
        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con2 = student1Cls.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con2);
        //调用构造方法
        Object obj = con2.newInstance();
          System.out.println("obj = " + obj);
          StudentConstructor stu = (StudentConstructor)obj;
        System.out.println("无参构造方法获得Student1: "+ stu);

        System.out.println("<<<<<<<<<<<<<<<<<<<<<获取私有构造方法，并调用>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Constructor con3= student1Cls.getDeclaredConstructor(char.class);
        System.out.println(con3);
        //调用构造方法
        con3.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con3.newInstance('男');
    }
}
