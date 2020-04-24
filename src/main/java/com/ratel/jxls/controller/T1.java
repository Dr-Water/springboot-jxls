package com.ratel.jxls.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/**
 * 简单表格--动态列的数据以及列的字段类型
 */
public class T1 {

    public static void main(String[] args) throws Exception {
        //构造集合数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> data1 = new ArrayList<Object>();
        data1.add("chendd");data1.add("男");data1.add(25);
        dataList.add(data1);
        List<Object> data2 = new ArrayList<Object>();
        data2.add("jiangjj");data2.add("男");data2.add(26);
        dataList.add(data2);
        List<Object> data3 = new ArrayList<Object>();
        data3.add("zengxr");data3.add("男");data3.add(27);
        dataList.add(data3);
        //载入模板
        //InputStream is = T1.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleGrid.xls");
        FileInputStream is = new FileInputStream(new File("F:\\templates\\竖列模板.xlsx"));
        Context context = new Context();
        context.putVar("headers", Arrays.asList("姓名" , "性别" , "年龄"));
        context.putVar("dataList", dataList);
        OutputStream os = new FileOutputStream(new File("F:\\templates\\result3.xlsx"));
        //指定Sheet文件解析
        JxlsHelper.getInstance().processTemplate(is, os, context);
        os.flush();
        os.close();
        is.close();
    }

}
