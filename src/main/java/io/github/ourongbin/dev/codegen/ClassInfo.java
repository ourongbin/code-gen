package io.github.ourongbin.dev.codegen;

import lombok.Data;

import java.util.List;

@Data
public class ClassInfo {

    private String tableName; // t_user_info
    private String className;
    private String classComment;
    private List<FieldInfo> fieldList;

    @Data
    public static class FieldInfo {

        private String columnName;
        private String fieldName;
        private String fieldClass;
        private String fieldComment;

    }
}
