package io.github.ourongbin.dev.codegen;

import freemarker.template.TemplateException;
import io.github.ourongbin.dev.codegen.config.CodeGenProperties;
import io.github.ourongbin.dev.codegen.util.TemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.capitalize;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeGenService {
    private final CodeGenProperties codeGenProperties;

    public String getTableSql() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(codeGenProperties.getSqlPath());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    /**
     * 根据配置的Template模板进行遍历解析，然后写入文件
     */
    public void processTemplates(Map<String, Object> params) throws IOException, TemplateException {
        for (CodeGenProperties.TemplateInfo ti : codeGenProperties.getTemplates()) {
            TemplateUtil.processTemplate(
                    ti.getName() + ".ftl",
                    codeGenProperties.getOutputPath() + File.separator
                            + capitalize(params.get(ti.getKey()).toString()) + capitalize(ti.getName()) + "." + ti.getType(),
                    params);
        }
    }

}
