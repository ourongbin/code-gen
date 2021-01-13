package io.github.ourongbin.dev.codegen;

import freemarker.template.TemplateException;
import io.github.ourongbin.dev.codegen.config.CodeGenProperties;
import io.github.ourongbin.dev.codegen.config.ParamInfo;
import io.github.ourongbin.dev.codegen.util.CodeGenException;
import io.github.ourongbin.dev.codegen.util.TableParseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({CodeGenProperties.class})
public class Application implements CommandLineRunner {

    private final CodeGenProperties codeGenProperties;
    private final CodeGenService codeGenService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws IOException, TemplateException {
//        Options options = new Options();
//
//        options.addRequiredOption("n", "threadnum", true, "并发执行的线程数, eg: 10");
//        options.addRequiredOption("a", "ipaddress", true, "http接口的ip地址, eg: 10.9.9.9");
//        options.addRequiredOption("i", "inputfile", true, "userid输入文件名 eg: ~/20190821UserCallbackRewardInput");
//        options.addRequiredOption("o", "outputfileprefix", true, "处理结果输出文件名前缀 eg: ~/20190821UserCallbackRewardOutput");
//
//        CommandLine cmdLine;
//        try {
//            CommandLineParser parser = new DefaultParser();
//            cmdLine = parser.parse(options, args);
//        } catch (ParseException e) {
//            System.out.println("Parsing failed.  Reason: " + e.getMessage());
//            System.out.println();
//            HelpFormatter formatter = new HelpFormatter();
//            formatter.printHelp("Usage ", options);
//            return;
//        }
//
//
//        int threadNum = Integer.parseInt(cmdLine.getOptionValue("n"));
//        String ipAddress = cmdLine.getOptionValue("a");
//        String inputFileName = cmdLine.getOptionValue("i");

        ParamInfo paramInfo = codeGenProperties.getParamInfo();
        String tableSql = codeGenService.getTableSql();
        if (StringUtils.isBlank(tableSql)) {
            throw new CodeGenException("建表sql不可为空");
        }

        //1.Parse Table Structure 表结构解析
        ClassInfo classInfo = TableParseUtil.processTableIntoClassInfo(tableSql, paramInfo);

        //2.Set the params 设置表格参数
        Map<String, Object> params = new HashMap<String, Object>(8);
        params.put("classInfo", classInfo);
        params.put("tableName", classInfo.getTableName());
        params.put("CapClassName", classInfo.getClassName());
        params.put("className", StringUtils.uncapitalize(classInfo.getClassName()));
        params.put("baseUrl", codeGenProperties.getBaseUrl());
        params.put("packageName", codeGenProperties.getPackageName());
        params.put("returnUtil", codeGenProperties.getFullReturnUtil().substring(codeGenProperties.getFullReturnUtil().lastIndexOf(".") + 1));
        params.put("pageRequest", codeGenProperties.getFullPageRequest().substring(codeGenProperties.getFullPageRequest().lastIndexOf(".") + 1));
        params.put("pageResponse", codeGenProperties.getFullPageResponse().substring(codeGenProperties.getFullPageResponse().lastIndexOf(".") + 1));
        params.put("bizException", codeGenProperties.getFullBizException().substring(codeGenProperties.getFullBizException().lastIndexOf(".") + 1));
        params.put("fullReturnUtil", codeGenProperties.getFullReturnUtil());
        params.put("fullPageRequest", codeGenProperties.getFullPageRequest());
        params.put("fullPageResponse", codeGenProperties.getFullPageResponse());
        params.put("fullBizException", codeGenProperties.getFullBizException());
        params.put("swagger", paramInfo.isSwagger());

        //3.generate the code by freemarker template and param . Freemarker根据参数和模板生成代码
        codeGenService.processTemplates(params);
    }
}