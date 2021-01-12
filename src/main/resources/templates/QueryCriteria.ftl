package ${packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
<#if swagger>
@ApiModel("${classInfo.classComment}查询条件")
</#if>
public class ${CapClassName}QueryCriteria {

}
