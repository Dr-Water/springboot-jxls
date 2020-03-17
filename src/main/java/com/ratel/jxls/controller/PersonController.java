package com.ratel.jxls.controller;

import com.ratel.jxls.entity.Person;
import com.ratel.jxls.utils.JxlsUtils;
import com.ratel.jxls.view.JxlsExcelView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @业务描述：
 * @package_name： com.ratel.jxls.controller
 * @project_name： springboot-jxls
 * @author： ratelfu@qq.com
 * @create_time： 2020-03-16 18:00
 * @copyright (c) ratelfu 版权所有
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    /**
     * 使用springmvc的view 进行导出简化操作
     * @return
     */
    @GetMapping("/export/jxls")
    public ModelAndView jxls() {
        Map<String,Object> map = new HashMap<String,Object>();
        ArrayList<Person> people = new ArrayList<>();
        Person zs = new Person("1001","zs", 12);
        Person ls = new Person("1002","ls", 13);
        Person ww = new Person("1003","ww", 14);
        people.add(zs);
        people.add(ls);
        people.add(ww);
        map.put("person", people);
        return   new ModelAndView(new JxlsExcelView("templates/1.xlsx","2"), map);
    }

    /**
     * 自己手动导出excel
     * @param response
     */
    @GetMapping("/export/jxls2")
    public void jxls3(HttpServletResponse response) {

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
        try {
            ServletOutputStream os = response.getOutputStream();
            response.setHeader("content-disposition", "attachment;filename=" + 3 + ".xls");
            response.setContentType("application/vnd.ms-excel");
                                                //注意这个地方是从磁盘路径取得模板
            JxlsUtils.exportExcel("F:/templates/1.xlsx", os, model);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
