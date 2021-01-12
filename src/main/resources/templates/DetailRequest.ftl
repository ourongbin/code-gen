package ${packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
<#if swagger>
@ApiModel("${classInfo.classComment}详情请求")
</#if>
public class ${CapClassName}DetailRequest {

<#list classInfo.fieldList as fieldItem >
  <#if fieldItem.fieldName == "id">
    <#if swagger>
    @ApiModelProperty(value = "${fieldItem.fieldComment}", required = true)
    <#else>
    /**
    * ${fieldItem.fieldComment}
    */
    </#if>
    @NotNull
    private ${fieldItem.fieldClass} ${fieldItem.fieldName};

  </#if>
</#list>
}
