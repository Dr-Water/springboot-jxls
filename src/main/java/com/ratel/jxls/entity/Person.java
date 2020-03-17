package com.ratel.jxls.entity;

/**
 * @业务描述：
 * @package_name： com.ratel.jxls.entity
 * @project_name： springboot-jxls
 * @author： ratelfu@qq.com
 * @create_time： 2020-03-09 15:08
 * @copyright (c) ratelfu 版权所有
 */
public class Person {
    String id;
    String name;
    Integer age;

    public Person() {
    }
    public Person(String id, String name, Integer age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}