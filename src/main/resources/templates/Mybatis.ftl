<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.${CapClassName}Mapper">

    <resultMap id="BaseResultMap" type="${packageName}.${CapClassName}DO">
    <#list classInfo.fieldList as fieldItem >
        <result column="${fieldItem.columnName}" property="${fieldItem.fieldName}"/>
    </#list>
    </resultMap>

    <sql id="Base_Column_List">
    <#list classInfo.fieldList as fieldItem >
        ${fieldItem.columnName}<#if fieldItem_has_next>,</#if>
    </#list>
    </sql>

    <insert id="insert" useGeneratedKeys="true" keyColumn="id" keyProperty="id" parameterType="${packageName}.${CapClassName}DO">
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
    <#list classInfo.fieldList as fieldItem >
        <#if fieldItem.columnName != "id" >
            ${r"<if test='null != "}${fieldItem.fieldName}${r"'>"}
                ${fieldItem.columnName}<#if fieldItem_has_next>,</#if>
            ${r"</if>"}
        </#if>
    </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
    <#list classInfo.fieldList as fieldItem >
        <#if fieldItem.columnName != "id" >
            ${r"<if test='null != "}${fieldItem.fieldName}${r"'>"}
                ${r"#{"}${fieldItem.fieldName}${r"}"}<#if fieldItem_has_next>,</#if>
            ${r"</if>"}
        </#if>
    </#list>
        </trim>
    </insert>

    <update id="deleteByIdList" >
        UPDATE ${tableName}
        SET is_deleted = 1
        WHERE is_deleted = 0 AND id IN
        <foreach collection="idList" item="id" open="(" close=")" separator=",">
            井{id}
        </foreach>
    </update>

    <update id="updateById" parameterType="${packageName}.${CapClassName}DO">
        UPDATE ${tableName}
        <set>
    <#list classInfo.fieldList as fieldItem >
        <#if fieldItem.columnName != "id" && fieldItem.columnName != "AddTime" && fieldItem.columnName != "UpdateTime" >
            ${r"<if test='null != "}${fieldItem.fieldName}${r"'>"}${fieldItem.columnName} = ${r"#{"}${fieldItem.fieldName}${r"}"}<#if fieldItem_has_next>,</#if>${r"</if>"}
        </#if>
    </#list>
        </set>
        WHERE id = ${r"#{"}id${r"}"} AND is_deleted = 0
    </update>


    <select id="selectById" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List"/>
        FROM ${tableName}
        WHERE id = ${r"#{id}"} AND is_deleted = 0
    </select>

    <select id="pageList" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List"/>
        FROM ${tableName}
        WHERE is_deleted = 0
        LIMIT ${r"#{offset}"}, ${r"#{limit}"}
    </select>

    <select id="pageListCount" resultType="java.lang.Integer">
        SELECT count(*)
        FROM ${tableName}
        WHERE is_deleted = 0
    </select>

</mapper>