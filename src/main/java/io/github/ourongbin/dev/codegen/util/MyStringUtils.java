package io.github.ourongbin.dev.codegen.util;

public class MyStringUtils {
    /**
     * 下划线，转换为驼峰式
     */
    public static String underlineToCamelCase(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.trim().length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 传入需要转义的字符串进行转义
     */
    public static String escapeString(String originStr) {
        return originStr.replaceAll("井", "\\#").replaceAll("￥", "\\$");
    }

}
