package com.anno.reflect;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: Student.java
 * @Description: 被反射解析的对象
 * @Package com.anno
 * @Time: 2021-06-27 22:05
 */
public class Student {

    private Integer stu_id;
    private String stu_name;
    private int age;

    /**
     * 无参构造器
     */
    public Student() {
        System.out.println("无参构造方法!\n");
    }
    public Student(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Student(Integer stu_id, String stu_name) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
    }

    public Student(Integer stu_id, String stu_name, int age) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.age = age;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "当前Student的信息： \n {" +
                "stu_id=" + stu_id +
                ", stu_name='" + stu_name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }
}
