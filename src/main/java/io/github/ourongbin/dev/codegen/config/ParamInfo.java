package io.github.ourongbin.dev.codegen.config;

import lombok.Data;

@Data
public class ParamInfo {
    /**
     * 命名转换规则
     */
    private String nameCaseType = NamingCaseType.CAMEL_CASE;
    /**
     * tinyint转换类型 : Integer int Boolean boolean
     */
    private String tinyintTransType = "Integer";
    /**
     * 时间转换类型
     */
    private String timeTransType = "Date";
    /**
     * 数据类型：ddl-sql json
     */
//    private String dataType;
    /**
     * 是否启用swagger
     */
    private boolean swagger = true;
    /**
     * 是否启用包装类型
     */
    private boolean packageType = true;

    public interface NamingCaseType {
        String CAMEL_CASE = "CamelCase";
        String UNDER_SCORE_CASE = "UnderScoreCase";
        String UPPER_UNDER_SCORE_CASE = "UpperUnderScoreCase";
    }

}
