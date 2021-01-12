package io.github.ourongbin.dev.codegen.util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class TemplateUtil {

    private static final Configuration freemarkerConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    static {
        try {
            //2020-06-21 zhengkai 修复path问题导致jar无法运行而本地项目可以运行的bug
            freemarkerConfig.setClassForTemplateLoading(TemplateUtil.class, "/templates");
            freemarkerConfig.setTemplateLoader(new ClassTemplateLoader(TemplateUtil.class, "/templates"));
            //freemarkerConfig.setDirectoryForTemplateLoading(new File(templatePath, "templates/code-generator"));
            freemarkerConfig.setNumberFormat("#");
            freemarkerConfig.setClassicCompatible(true);
            freemarkerConfig.setDefaultEncoding("UTF-8");
            freemarkerConfig.setLocale(Locale.CHINA);
            freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void processTemplate(String templateName, String outputName, Map<String, Object> params) throws IOException, TemplateException {
        Template template = freemarkerConfig.getTemplate(templateName);

        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);
        String s = MyStringUtils.escapeString(stringWriter.toString());

        File file = new File(outputName);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new CodeGenException("创建输出目录失败");
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(s);
        }
        log.info("生成: {}", file.getName());
    }

}
