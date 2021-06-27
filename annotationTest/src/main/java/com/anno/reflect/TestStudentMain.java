package com.anno.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: TestStudentMain.java
 * @Description: 解析StudentMain过程
 * @Package com.anno.reflect
 * @Time: 2021-06-27 23:02
 */
public class TestStudentMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //1、获取Student对象的字节码
        Class clazz = Class.forName("com.anno.reflect.StudentMain");

        //2、获取main方法
        Method methodMain = clazz.getMethod("main", String[].class);//第一个参数：方法名称，第二个参数：方法形参的类型，
        //3、调用main方法
        // methodMain.invoke(null, new String[]{"a","b","c"});
        //第一个参数，对象类型，因为方法是static静态的，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
        //这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
        methodMain.invoke(null, (Object)new String[]{"a","b","c"});//方式一
        // methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二
    }
}
