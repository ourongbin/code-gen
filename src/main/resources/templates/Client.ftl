package ${packageName};

import com.tencent.sr.rmall.common.primitive.HttpResult;
import com.tencent.sr.rmall.common.request.PaginationRequest;
import com.tencent.sr.rmall.common.response.PaginationResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("spring-application-name")
public interface ${CapClassName}Client {

    @PostMapping("${baseUrl}/${className}/save")
    @ApiOperation("保存，id为null时创建")
    ${returnUtil}<Void> save(@RequestBody @Valid ${CapClassName}SaveRequest req);

    @ApiOperation("删除")
    @PostMapping("${baseUrl}/${className}/delete")
    ${returnUtil}<Void> delete(@RequestBody @Valid ${CapClassName}DelRequest req);

    @ApiOperation("分页查询")
    @PostMapping("${baseUrl}/${className}/query")
    ${returnUtil}<${pageResponse}<${CapClassName}DTO>> pageQuery(@RequestBody @Valid ${pageRequest}<${CapClassName}QueryCriteria> req);

    @ApiOperation("详情")
    @PostMapping("${baseUrl}/${className}/detail")
    ${returnUtil}<${CapClassName}DTO> detail(@RequestBody @Valid ${CapClassName}DetailRequest req);

}
