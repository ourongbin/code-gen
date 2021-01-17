package ${packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
<#if swagger>
@ApiModel("${classInfo.classComment}")
</#if>
public class ${CapClassName}VO {

<#list classInfo.fieldList as fieldItem >
    <#if swagger>
    @ApiModelProperty("${fieldItem.fieldComment}")
    <#else>
    /**
    * ${fieldItem.fieldComment}
    */
    </#if>
    private ${fieldItem.fieldClass} ${fieldItem.fieldName};

</#list>

}
