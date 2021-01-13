package ${packageName};

import ${fullPageRequest};
import ${fullPageResponse};
import ${fullReturnUtil};
import ${fullBizException};
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ${CapClassName}Dependency {
    private final ${CapClassName}Client client;

    public void save(${CapClassName}SaveRequest request) {
        ${returnUtil}<Void> result = client.save(request);
        if (!result.isSuccess()) {
            throw new ${bizException}(result.getCode(), result.getMsg());
        }
    }

    public void delete(${CapClassName}DelRequest request) {
        ${returnUtil}<Void> result = client.delete(request);
        if (!result.isSuccess()) {
            throw new ${bizException}(result.getCode(), result.getMsg());
        }
    }

    public ${CapClassName}DTO detail(${CapClassName}DetailRequest request) {
        ${returnUtil}<${CapClassName}DTO> result = client.detail(request);
        if (result.isSuccess()) {
            return result.getData();
        }
        return null;
    }

    public ${pageResponse}<${CapClassName}DTO> pageQuery(${pageRequest}<${CapClassName}QueryCriteria> request) {
        ${returnUtil}<${pageResponse}<${CapClassName}DTO>> result = client.pageQuery(request);
        if (result.isSuccess()) {
            return result.getData();
        }
        return null;
    }
}
