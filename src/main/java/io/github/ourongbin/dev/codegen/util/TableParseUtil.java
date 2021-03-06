package io.github.ourongbin.dev.codegen.util;


import io.github.ourongbin.dev.codegen.ClassInfo;
import io.github.ourongbin.dev.codegen.config.ParamInfo;
import io.github.ourongbin.dev.codegen.util.CodeGenException;
import io.github.ourongbin.dev.codegen.util.MyStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableParseUtil {

    /**
     * 解析DDL SQL生成类信息
     */
    public static ClassInfo processTableIntoClassInfo(String tableSql, ParamInfo paramInfo) throws IOException {
        //process the param
        String nameCaseType = paramInfo.getNameCaseType();

        if (StringUtils.isBlank(tableSql)) {
            throw new CodeGenException("Table structure can not be empty. 表结构不能为空。");
        }
        tableSql = tableSql.trim();
        //deal with special character
        tableSql = tableSql.replaceAll("'", "`").replaceAll("\"", "`").replaceAll("，", ",").toLowerCase();
        //deal with java string copy \n"
        tableSql = tableSql.replaceAll("\\\\n`", "").replaceAll("\\+", "").replaceAll("``", "`").replaceAll("\\\\", "");
        // table Name
        String tableName = null;
        if (tableSql.contains("TABLE") && tableSql.contains("(")) {
            tableName = tableSql.substring(tableSql.indexOf("TABLE") + 5, tableSql.indexOf("("));
        } else if (tableSql.contains("table") && tableSql.contains("(")) {
            tableName = tableSql.substring(tableSql.indexOf("table") + 5, tableSql.indexOf("("));
        } else {
            throw new CodeGenException("Table structure incorrect.表结构不正确。");
        }

        //新增处理create table if not exists members情况
        if (tableName.contains("if not exists")) {
            tableName = tableName.replaceAll("if not exists", "");
        }

        if (tableName.contains("`")) {
            tableName = tableName.substring(tableName.indexOf("`") + 1, tableName.lastIndexOf("`"));
        } else {
            //需要替换掉\n\t空格
            tableName = tableName.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
        }
        //优化对byeas`.`ct_bd_customerdiscount这种命名的支持
        if (tableName.contains("`.`")) {
            tableName = tableName.substring(tableName.indexOf("`.`") + 3);
        } else if (tableName.contains(".")) {
            //优化对likeu.members这种命名的支持
            tableName = tableName.substring(tableName.indexOf(".") + 1);
        }
        // class Name
        String className = StringUtils.capitalize(MyStringUtils.underlineToCamelCase(tableName));
        if (className.contains("_")) {
            className = className.replaceAll("_", "");
        }

        // class Comment
        String classComment;
        //mysql是comment=,pgsql/oracle是comment on table,
        //2020-05-25 优化表备注的获取逻辑
        if (tableSql.contains("comment =") || tableSql.contains("comment on table")) {
            String classCommentTmp = (tableSql.contains("comment =")) ?
                    tableSql.substring(tableSql.lastIndexOf("comment =") + 9).trim() : tableSql.substring(tableSql.lastIndexOf("comment on table") + 17).trim();
            if (classCommentTmp.contains("`")) {
                classCommentTmp = classCommentTmp.substring(classCommentTmp.indexOf("`") + 1);
                classCommentTmp = classCommentTmp.substring(0, classCommentTmp.indexOf("`"));
                classComment = classCommentTmp;
            } else {
                //非常规的没法分析
                classComment = className;
            }
        } else {
            //修复表备注为空问题
            classComment = tableName;
        }
        //如果备注跟;混在一起，需要替换掉
        classComment = classComment.replaceAll(";", "");
        // field List
        List<ClassInfo.FieldInfo> fieldList = new ArrayList<>();

        // 正常( ) 内的一定是字段相关的定义。
        String fieldListTmp = tableSql.substring(tableSql.indexOf("(") + 1, tableSql.lastIndexOf(")"));

        // 匹配 comment，替换备注里的小逗号, 防止不小心被当成切割符号切割
        String commentPattenStr1 = "comment `(.*?)`";
        Matcher matcher1 = Pattern.compile(commentPattenStr1).matcher(fieldListTmp);
        while (matcher1.find()) {

            String commentTmp = matcher1.group();
            //2018-9-27 zhengk 不替换，只处理，支持COMMENT评论里面多种注释
            //commentTmp = commentTmp.replaceAll("\\ comment `|\\`", " ");      // "\\{|\\}"

            if (commentTmp.contains(",")) {
                String commentTmpFinal = commentTmp.replaceAll(",", "，");
                fieldListTmp = fieldListTmp.replace(matcher1.group(), commentTmpFinal);
            }
        }
        //2018-10-18 zhengkai 新增支持double(10, 2)等类型中有英文逗号的特殊情况
        String commentPattenStr2 = "`(.*?)`";
        Matcher matcher2 = Pattern.compile(commentPattenStr2).matcher(fieldListTmp);
        while (matcher2.find()) {
            String commentTmp2 = matcher2.group();
            if (commentTmp2.contains(",")) {
                String commentTmpFinal = commentTmp2.replaceAll(",", "，").replaceAll("\\(", "（").replaceAll("\\)", "）");
                fieldListTmp = fieldListTmp.replace(matcher2.group(), commentTmpFinal);
            }
        }
        //2018-10-18 zhengkai 新增支持double(10, 2)等类型中有英文逗号的特殊情况
        String commentPattenStr3 = "\\((.*?)\\)";
        Matcher matcher3 = Pattern.compile(commentPattenStr3).matcher(fieldListTmp);
        while (matcher3.find()) {
            String commentTmp3 = matcher3.group();
            if (commentTmp3.contains(",")) {
                String commentTmpFinal = commentTmp3.replaceAll(",", "，");
                fieldListTmp = fieldListTmp.replace(matcher3.group(), commentTmpFinal);
            }
        }
        String[] fieldLineList = fieldListTmp.split(",");
        if (fieldLineList.length > 0) {
            int i = 0;
            //i为了解决primary key关键字出现的地方，出现在前3行，一般和id有关
            for (String columnLine : fieldLineList) {
                i++;
                columnLine = columnLine.replaceAll("\n", "").replaceAll("\t", "").trim();
                // `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                // 2018-9-18 zhengk 修改为contains，提升匹配率和匹配不按照规矩出牌的语句
                // 2018-11-8 zhengkai 修复tornadoorz反馈的KEY FK_permission_id (permission_id),KEY FK_role_id (role_id)情况
                // 2019-2-22 zhengkai 要在条件中使用复杂的表达式
                // 2019-4-29 zhengkai 优化对普通和特殊storage关键字的判断（感谢@AhHeadFloating的反馈 ）
                // 2020-10-20 zhengkai 优化对fulltext/index关键字的处理（感谢@WEGFan的反馈）
                boolean specialFlag = (!columnLine.contains("key ") && !columnLine.contains("constraint") && !columnLine.contains("using") && !columnLine.contains("unique ")
                        && !(columnLine.contains("primary ") && columnLine.indexOf("storage") + 3 > columnLine.indexOf("("))
                        && !columnLine.contains("fulltext ") && !columnLine.contains("index ")
                        && !columnLine.contains("pctincrease")
                        && !columnLine.contains("buffer_pool") && !columnLine.contains("tablespace")
                        && !(columnLine.contains("primary ") && i > 3));
                if (specialFlag) {
                    //如果是oracle的number(x,x)，可能出现最后分割残留的,x)，这里做排除处理
                    if (columnLine.length() < 5) {
                        continue;
                    }
                    //2018-9-16 zhengkai 支持'符号以及空格的oracle语句// userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                    String columnName;
                    columnLine = columnLine.replaceAll("`", " ").replaceAll("\"", " ").replaceAll("'", "").replaceAll(" {2}", " ").trim();
                    //如果遇到username varchar(65) default '' not null,这种情况，判断第一个空格是否比第一个引号前
                    columnName = columnLine.substring(0, columnLine.indexOf(" "));
                    // field Name
                    // 2019-09-08 yj 添加是否下划线转换为驼峰的判断
                    String fieldName;
                    if (ParamInfo.NamingCaseType.CAMEL_CASE.equals(nameCaseType)) {
                        fieldName = StringUtils.uncapitalize(MyStringUtils.underlineToCamelCase(columnName));
                        if (fieldName.contains("_")) {
                            fieldName = fieldName.replaceAll("_", "");
                        }
                    } else if (ParamInfo.NamingCaseType.UNDER_SCORE_CASE.equals(nameCaseType)) {
                        fieldName = StringUtils.uncapitalize(columnName);
                    } else if (ParamInfo.NamingCaseType.UPPER_UNDER_SCORE_CASE.equals(nameCaseType)) {
                        fieldName = StringUtils.uncapitalize(columnName.toUpperCase());
                    } else {
                        fieldName = columnName;
                    }

                    // field class
                    columnLine = columnLine.substring(columnLine.indexOf("`") + 1).trim();
                    // int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                    String fieldClass;
                    //2018-9-16 zhengk 补充char/clob/blob/json等类型，如果类型未知，默认为String
                    //2018-11-22 lshz0088 处理字段类型的时候，不严谨columnLine.contains(" int") 类似这种的，可在前后适当加一些空格之类的加以区分，否则当我的字段包含这些字符的时候，产生类型判断问题。
                    //2020-05-03 MOSHOW.K.ZHENG 优化对所有类型的处理
                    //2020-10-20 zhengkai 新增包装类型的转换选择
                    if (columnLine.contains(" tinyint")) {
                        //20191115 MOSHOW.K.ZHENG 支持对tinyint的特殊处理
                        fieldClass = paramInfo.getTinyintTransType();
                    } else if (columnLine.contains(" int") || columnLine.contains(" smallint")) {
                        fieldClass = (paramInfo.isPackageType()) ? Integer.class.getSimpleName() : "int";
                    } else if (columnLine.contains(" bigint")) {
                        fieldClass = (paramInfo.isPackageType()) ? Long.class.getSimpleName() : "long";
                    } else if (columnLine.contains(" float")) {
                        fieldClass = (paramInfo.isPackageType()) ? Float.class.getSimpleName() : "float";
                    } else if (columnLine.contains(" double")) {
                        fieldClass = (paramInfo.isPackageType()) ? Double.class.getSimpleName() : "double";
                    } else if (columnLine.contains(" time") || columnLine.contains(" date") || columnLine.contains(" datetime") || columnLine.contains(" timestamp")) {
                        fieldClass = paramInfo.getTimeTransType();
                    } else if (columnLine.contains(" varchar") || columnLine.contains(" text") || columnLine.contains(" char")
                            || columnLine.contains(" clob") || columnLine.contains(" blob") || columnLine.contains(" json")) {
                        fieldClass = String.class.getSimpleName();
                    } else if (columnLine.contains(" decimal") || columnLine.contains(" number")) {
                        //2018-11-22 lshz0088 建议对number类型增加int，long，BigDecimal的区分判断
                        //如果startKh大于等于0，则表示有设置取值范围
                        int startKh = columnLine.indexOf("(");
                        if (startKh >= 0) {
                            int endKh = columnLine.indexOf(")", startKh);
                            String[] fanwei = columnLine.substring(startKh + 1, endKh).split("，");
                            //2019-1-5 zhengk 修复@arthaschan反馈的超出范围错误
                            //System.out.println("fanwei"+ JSON.toJSONString(fanwei));
                            //                            //number(20,6) fanwei["20","6"]
                            //                            //number(0,6) fanwei["0","6"]
                            //                            //number(20,0) fanwei["20","0"]
                            //                            //number(20) fanwei["20"]
                            //如果括号里是1位或者2位且第二位为0，则进行特殊处理。只有有小数位，都设置为BigDecimal。
                            if ((fanwei.length > 1 && "0".equals(fanwei[1])) || fanwei.length == 1) {
                                int length = Integer.parseInt(fanwei[0]);
                                if (fanwei.length > 1) {
                                    length = Integer.parseInt(fanwei[1]);
                                }
                                //数字范围9位及一下用Integer，大的用Long
                                if (length <= 9) {
                                    fieldClass = (paramInfo.isPackageType()) ? Integer.class.getSimpleName() : "int";
                                } else {
                                    fieldClass = (paramInfo.isPackageType()) ? Long.class.getSimpleName() : "long";
                                }
                            } else {
                                //有小数位数一律使用BigDecimal
                                fieldClass = BigDecimal.class.getSimpleName();
                            }
                        } else {
                            fieldClass = BigDecimal.class.getSimpleName();
                        }
                    } else if (columnLine.contains(" boolean")) {
                        //20190910 MOSHOW.K.ZHENG 新增对boolean的处理（感谢@violinxsc的反馈）以及修复tinyint类型字段无法生成boolean类型问题（感谢@hahaYhui的反馈）
                        fieldClass = (paramInfo.isPackageType()) ? Boolean.class.getSimpleName() : "boolean";
                    } else {
                        fieldClass = String.class.getSimpleName();
                    }

                    // field comment，MySQL的一般位于field行，而pgsql和oralce多位于后面。
                    String fieldComment;
                    if (tableSql.contains("comment on column") && (tableSql.contains("." + columnName + " is ") || tableSql.contains(".`" + columnName + "` is"))) {
                        //新增对pgsql/oracle的字段备注支持
                        //COMMENT ON COLUMN public.check_info.check_name IS '检查者名称';
                        //2018-11-22 lshz0088 正则表达式的点号前面应该加上两个反斜杠，否则会认为是任意字符
                        //2019-4-29 zhengkai 优化对oracle注释comment on column的支持（@liukex）
                        tableSql = tableSql.replaceAll(".`" + columnName + "` is", "." + columnName + " is");
                        Matcher columnCommentMatcher = Pattern.compile("\\." + columnName + " is `").matcher(tableSql);
                        fieldComment = columnName;
                        while (columnCommentMatcher.find()) {
                            String columnCommentTmp = columnCommentMatcher.group();
                            //System.out.println(columnCommentTmp);
                            fieldComment = tableSql.substring(tableSql.indexOf(columnCommentTmp) + columnCommentTmp.length()).trim();
                            fieldComment = fieldComment.substring(0, fieldComment.indexOf("`")).trim();
                        }
                    } else if (columnLine.contains(" comment")) {
                        //20200518 zhengkai 修复包含comment关键字的问题
                        String commentTmp = columnLine.substring(columnLine.lastIndexOf("comment") + 7).trim();
                        // '用户ID',
                        if (commentTmp.contains("`") || commentTmp.indexOf("`") != commentTmp.lastIndexOf("`")) {
                            commentTmp = commentTmp.substring(commentTmp.indexOf("`") + 1, commentTmp.lastIndexOf("`"));
                        }
                        //解决最后一句是评论，无主键且连着)的问题:album_id int(3) default '1' null comment '相册id：0 代表头像 1代表照片墙')
                        if (commentTmp.contains(")")) {
                            commentTmp = commentTmp.substring(0, commentTmp.lastIndexOf(")") + 1);
                        }
                        fieldComment = commentTmp;
                    } else {
                        //修复comment不存在导致报错的问题
                        fieldComment = columnName;
                    }

                    ClassInfo.FieldInfo fieldInfo = new ClassInfo.FieldInfo();
                    fieldInfo.setColumnName(columnName);
                    fieldInfo.setFieldName(fieldName);
                    fieldInfo.setFieldClass(fieldClass);
                    fieldInfo.setFieldComment(fieldComment);

                    fieldList.add(fieldInfo);
                }
            }
        }

        if (fieldList.size() < 1) {
            throw new CodeGenException("表结构分析失败，请检查语句或者提交issue给我");
        }

        if (tableName.startsWith("t_")) {
            className = className.substring(1);
        }
        ClassInfo codeJavaInfo = new ClassInfo();
        codeJavaInfo.setTableName(tableName);
        codeJavaInfo.setClassName(className);
        codeJavaInfo.setClassComment(classComment);
        codeJavaInfo.setFieldList(fieldList);

        return codeJavaInfo;
    }

}
