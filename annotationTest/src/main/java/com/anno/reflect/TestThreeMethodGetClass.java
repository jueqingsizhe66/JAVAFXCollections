package com.anno.reflect;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: TestThreeMethodGetClass.java
 * @Description: (用一句话描述该文件做什么 ?)
 * @Package com.anno
 * @Time: 2021-06-27 22:04
 */
public class TestThreeMethodGetClass {
    public static void main(String[] args) {
        Student stu = new Student(001,"yzl",30);
        /**
         * 方法1： 获取Class对象
         */
        Class cls =stu.getClass();
        System.out.println("方法1: "+cls.getName());
        /**
         * 方法2. 获取Class对象
         */
        Class cls2 =Student.class;
        System.out.println("方法2: "+cls.getName());
        /**
         * 方法3. 获取Class对象.根据全路径类进行class对象获取
         */
        try {
            Class cls3= Class.forName("com.anno.reflect.Student");
            System.out.println("方法3: "+cls3.getName());
            System.out.println(cls3 == cls2);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
