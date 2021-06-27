package com.anno.reflect;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: StudentFields.java
 * @Description: 解析反射字段过程
 * @Package com.anno
 * @Time: 2021-06-27 22:35
 */
public class StudentFields {
    public StudentFields(){

    }
    /**
     * 所有类型字段
     */
    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    @Override
    public String toString() {
        return "StudentFields{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
    /*
     * 获取成员变量并调用：
     *
     * 1.批量的
     *      1).Field[] getFields():获取所有的"公有字段"
     *      2).Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有；
     * 2.获取单个的：
     *      1).public Field getField(String fieldName):获取某个"公有的"字段；
     *      2).public Field getDeclaredField(String fieldName):获取某个字段(可以是私有的)
     *
     *   设置字段的值：
     *      Field --> public void set(Object obj,Object value):
     *                  参数说明：
     *                  1.obj:要设置的字段所在的对象；
     *                  2.value:要为字段设置的值；
     *
     */
}
