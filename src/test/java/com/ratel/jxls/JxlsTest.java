package com.ratel.jxls;

import com.ratel.jxls.entity.Person;
import com.ratel.jxls.utils.JxlsUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @业务描述：
 * @package_name： com.ratel.jxls
 * @project_name： springboot-jxls
 * @author： ratelfu@qq.com
 * @create_time： 2020-03-09 15:10
 * @copyright (c) ratelfu 版权所有
 */
public class JxlsTest {
    public static void main(String[] args) throws Exception  {
        // 模板位置，输出流
        String templatePath = "F:/templates/1.xlsx";
        OutputStream os = new FileOutputStream("F:/templates/2.xlsx");

        // 一个装有对象数据的链表
        List<Person> persons = new ArrayList<Person>();
        Person p1 = new Person("001", "张三", 18);
        Person p2 = new Person("002", "李四", 19);
        Person p3 = new Person("003", "王五", 20);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("person", persons);    // 把链表放进model中

        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();
        System.out.println("完成");

    }
    @Test
    public void t1(){
        Person person = new Person("1002", "王五", 56);
        Field[] declaredFields = person.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
    }
}
