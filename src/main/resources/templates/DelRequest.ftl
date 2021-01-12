package ${packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
<#if swagger>
@ApiModel("${classInfo.classComment}删除请求")
</#if>
public class ${CapClassName}DelRequest {

<#list classInfo.fieldList as fieldItem >
  <#if fieldItem.fieldName == "id">
    <#if swagger>
    @ApiModelProperty(value = "${fieldItem.fieldComment}List", required = true)
    <#else>
    /**
    * ${fieldItem.fieldComment}List
    */
    </#if>
    @NotNull
    @NotEmpty
    private List<${fieldItem.fieldClass}> ${fieldItem.fieldName}List;

  </#if>
</#list>
}
