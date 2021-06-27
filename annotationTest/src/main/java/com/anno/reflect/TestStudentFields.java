package com.anno.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: TestStudentFields.java
 * @Description: (用一句话描述该文件做什么 ?)
 * @Package com.anno
 * @Time: 2021-06-27 22:37
 */
public class TestStudentFields {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1. 获取反射类Class对象
        Class studnetFieldCls = Class.forName("com.anno.reflect.StudentFields");
        // 2. 获取所有共有字段
        System.out.println("<<<<<<<<<<<<<<<<<<<<<获取所有公有的字段>>>>>>>>>>>>>>>>>>>>>>>");
        Field[] fieldArray = studnetFieldCls.getFields();
        for (Field field : fieldArray) {
            System.out.println(field);
        }

        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = studnetFieldCls.getDeclaredFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }

        System.out.println("*************获取公有字段**并调用***********************************");
        Field f = studnetFieldCls.getField("name");
        /**
         * 字段名字即可， 不需要跟字段的类型！ 方法需要方法名和字段名，两个条件！
         */
        System.out.println(f);
        //获取一个对象
        Object obj = studnetFieldCls.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
        //为字段设置值
        /**
         * 第一次体味到反射其实把类的所有属性和方法，变成一个一个对象了！
         */
        f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
        //验证
        StudentFields stu = (StudentFields) obj;
        System.out.println("验证姓名：" + stu.name);

        System.out.println("**************获取私有字段****并调用********************************");
        f = studnetFieldCls.getDeclaredField("phoneNum");
        System.out.println(f);
        f.setAccessible(true);//暴力反射，解除私有限定
        f.set(obj, "18888889999");
        System.out.println("验证电话：" + stu);
        /**
         * 小结：
         *Object obj = stuClass.getConstructor().newInstance();
         * 产生Student对象--》Student stu = new Student();
         * 为字段设置值
         *    f.set(obj, "刘德华");
         *         为Student对象中的name属性赋值--》stu.name = "刘德华"
         *  -  第一个参数：要传入设置的对象，
         *  -  第二个参数：要传入实参
         */
    }
}
