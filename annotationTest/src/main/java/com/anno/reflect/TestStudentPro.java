package com.anno.reflect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author Zhaoliang Ye 叶昭良(zl_ye@qny.chng.com.cn)
 * @version V0.1
 * @Title: TestStudentPro.java
 * @Description: 通过配置文件调用StudentPro对象
 * @Package com.anno.reflect
 * @Time: 2021-06-27 23:21
 */
public class TestStudentPro {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //通过反射获取Class对象  通过getValue获取className对应的全路径类
        Class stuClass = Class.forName(getValue("className"));//"cn.fanshe.Student"
        //2获取show()方法, 通过getValue获得方法名字methodName对应右边字段
        Method m = stuClass.getMethod(getValue("methodName"));//show
        //3.调用show()方法
        m.invoke(stuClass.getConstructor().newInstance());    
    }

    /**
     * 此方法接收一个key,在配置文件获取相应值，可以是类名、方法名
     * @param key
     * @return
     */
    private static String getValue(String key) throws IOException {
        Properties properties =new Properties();
        /**
         * 必须得使用getClassLoader()类加载之后，才能获取pro.txt
         * https://www.cnblogs.com/blogtech/p/11151780.html
         * 而类加载器默认是从 classPath 下获取资源的，因为这下面有class文件。
         * getClassLoader()采用相对路径
         * 不加getClassLoader()采用绝对路径，得加上斜杠/
         */
        properties.load(TestStudentPro.class.getClassLoader().getResourceAsStream("pro.txt"));
        return properties.getProperty(key);
    }
}
