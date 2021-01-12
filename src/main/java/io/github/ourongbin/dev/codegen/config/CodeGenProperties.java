package io.github.ourongbin.dev.codegen.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@Data
@ConfigurationProperties("code-gen")
public class CodeGenProperties {

    private String baseUrl = "/api/v1";
    private String packageName = "com.tencent.sr";
    private String sqlPath = "/Users/ronou/input.sql";
    private String outputPath = "/Users/ronou/output";
    private String fullReturnUtil = "com.tencent.sr.rmall.common.primitive.HttpResult";
    private String fullPageRequest = "com.tencent.sr.rmall.common.request.PaginationRequest";
    private String fullPageResponse = "com.tencent.sr.rmall.common.response.PaginationResponse";
    private ParamInfo paramInfo = new ParamInfo();
    private List<TemplateInfo> templates = Collections.emptyList();

    @Data
    public static class TemplateInfo {
        private String name; // DO/DTO/Controller
        private String type = "java"; // java/xml
        private String key = "className"; // className
    }
}
