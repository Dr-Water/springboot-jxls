package com.ratel.jxls.view;

import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @业务描述：
 * @package_name： com.ratel.jxls.view
 * @project_name： springboot-jxls
 * @author： ratelfu@qq.com
 * @create_time： 2020-03-16 18:02
 * @copyright (c) ratelfu 版权所有
 */
public class JxlsExcelView extends AbstractView {
    private static final String CONTENT_TYPE = "application/vnd.ms-excel";

    private String templatePath;

    private String exportFileName;

    public JxlsExcelView() {
    }

    /**
     * @param templatePath 模版相对于当前classpath路径
     * @param exportFileName 导出文件名
     */
    public JxlsExcelView(String templatePath, String exportFileName) {
        this.templatePath = templatePath;
        this.exportFileName = exportFileName;
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        InputStream is = null;
        OutputStream os = null;

        os = response.getOutputStream();
        response.setContentType(getContentType());

        // 解决导出文件名中文乱码
        String filename = new String(exportFileName.getBytes("gb2312"), "iso8859-1");
        response.setHeader("content-disposition", "attachment;filename=" + filename + ".xls");

        // 获取excel模板
        //获取项目根路径下的文件输入流
        is = JxlsExcelView.class.getClassLoader().getResourceAsStream(templatePath);
        // 获取磁盘下文件输入流，比如 F:/templates/1.xlsx
        //is = new FileInputStream(templatePath);
        Context context = PoiTransformer.createInitialContext();
        if (model != null) {
            for (String key : model.keySet()) {
                context.putVar(key, model.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer  = jxlsHelper.createTransformer(is, os);
        //获得配置
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
        //设置静默模式，不报警告
        //evaluator.getJexlEngine().setSilent(true);
        //函数强制，自定义功能
        Map<String, Object> funcs = new HashMap<String, Object>();
        //添加自定义功能
        funcs.put("utils", new JxlsExcelView());
        evaluator.getJexlEngine().setFunctions(funcs);
        //必须要这个，否者表格函数统计会错乱
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
        os.flush();
        os.close();
        is.close();
    }
}
