package ${packageName};

import ${fullReturnUtil};
import ${fullPageRequest};
import ${fullPageResponse};
import ${packageName}.${CapClassName}Client;
import ${packageName}.${CapClassName}Service;
import ${packageName}.${CapClassName}DTO;
import ${packageName}.${CapClassName}SaveRequest;
import ${packageName}.${CapClassName}DelRequest;
import ${packageName}.${CapClassName}QueryCriteria;
import ${packageName}.${CapClassName}DetailRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ${CapClassName}Controller implements ${CapClassName}Client {

    private final ${CapClassName}Service service;

    @PostMapping("${baseUrl}/${className}/save")
    @ApiOperation("保存，id为null时创建")
    @Override
    public ${returnUtil}<Void> save(@RequestBody @Valid ${CapClassName}SaveRequest req) {
        service.save(req);
        return ${returnUtil}.succ(null);
    }

    @ApiOperation("删除")
    @PostMapping("${baseUrl}/${className}/delete")
    @Override
    public ${returnUtil}<Void> delete(@RequestBody @Valid ${CapClassName}DelRequest req) {
        service.delete(req);
        return ${returnUtil}.succ(null);
    }

    @ApiOperation("分页查询")
    @PostMapping("${baseUrl}/${className}/query")
    @Override
    public ${returnUtil}<${pageResponse}<${CapClassName}DTO>> pageQuery(@RequestBody @Valid ${pageRequest}<${CapClassName}QueryCriteria> req) {
        return ${returnUtil}.succ(service.pageQuery(req));
    }

    @ApiOperation("详情")
    @PostMapping("${baseUrl}/${className}/detail")
    @Override
    public ${returnUtil}<${CapClassName}DTO> detail(@RequestBody @Valid ${CapClassName}DetailRequest req) {
        return ${returnUtil}.succ(service.detail(req));
    }
}
