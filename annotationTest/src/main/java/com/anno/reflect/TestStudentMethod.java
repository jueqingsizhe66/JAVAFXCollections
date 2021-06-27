package com.anno.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: TestStudentMethod.java
 * @Description: 测试反射方法对象
 * @Package com.anno
 * @Time: 2021-06-27 22:48
 */
public class TestStudentMethod {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.获取Class对象
        Class stuClass = Class.forName("com.anno.reflect.StudentMethod");
        //2.获取所有公有方法
        System.out.println("***************获取所有的”公有“方法*******************");
        stuClass.getMethods();
        Method[] methodArray = stuClass.getMethods();
        for(Method m : methodArray){
            System.out.println(m);
        }
        System.out.println("***************获取所有的方法，包括私有的*******************");
        methodArray = stuClass.getDeclaredMethods();
        for(Method m : methodArray){
            System.out.println(m);
        }
        System.out.println("***************获取公有的show1public()方法*******************");
        /**
         * 方法名字+ 参数类型
         */
        Method m = stuClass.getMethod("show1public", String.class); /*修改了方法名字了 注意对应修改*/
        System.out.println(m);
        //实例化一个Student对象
        Object obj = stuClass.getConstructor().newInstance();
        m.invoke(obj, "陈得水");

        System.out.println("***************获取私有的show4private()方法******************");
        m = stuClass.getDeclaredMethod("show4private", int.class);
        System.out.println(m);
        m.setAccessible(true);//解除私有限定
        Object result = m.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println("返回值：" + result);

    }
}
