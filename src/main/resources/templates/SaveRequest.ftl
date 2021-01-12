package ${packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
<#if swagger>
@ApiModel("${classInfo.classComment}保存请求")
</#if>
public class ${CapClassName}SaveRequest {

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
