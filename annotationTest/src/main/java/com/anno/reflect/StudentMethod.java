package com.anno.reflect;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: StudentMethod.java
 * @Description: 解析反射方法过程
 * @Package com.anno
 * @Time: 2021-06-27 22:46
 */
public class StudentMethod {
    //**************成员方法***************//
    public void show1public(String s){
        System.out.println("调用了：公有的，String参数的show1public(): s = " + s);
    }
    protected void show2protected(){
        System.out.println("调用了：受保护的，无参的show2protected()");
    }
    void show3default(){
        System.out.println("调用了：默认的，无参的show3default()");
    }
    private String show4private(int age){
        System.out.println("调用了，私有的，并且有返回值的，int参数的show4private(): age = " + age);
        return "abcd";
    }
}
/*
 * 获取成员方法并调用：
 *
 * 1.批量的：
 *      public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
 *      public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
 * 2.获取单个的：
 *      public Method getMethod(String name,Class<?>... parameterTypes):
 *                  参数：
 *                      name : 方法名；
 *                      Class ... : 形参的Class类型对象
 *      public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
 *
 *   调用方法：
 *      Method --> public Object invoke(Object obj,Object... args):
 *                  参数说明：
 *                  obj : 要调用方法的对象；
 *                  args:调用方式时所传递的实参；

):
 */
