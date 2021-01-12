package ${packageName};

import ${fullPageRequest};
import ${fullPageResponse};
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
public class ${CapClassName}Service {
    private final ${CapClassName}Mapper mapper;

    public void save(${CapClassName}SaveRequest request) {
        ${CapClassName}DO dto = dto2do(request);
        if (request.getId() == null) {
            mapper.insert(dto);
        } else {
            mapper.updateById(dto);
        }
    }

    private ${CapClassName}DTO do2dto(${CapClassName}DO copy) {
        if (copy == null) {
            return null;
        }

        // todo
        ${CapClassName}DTO result = new ${CapClassName}DTO();
<#list classInfo.fieldList as fieldItem >
        result.set${fieldItem.fieldName?cap_first}(copy.get${fieldItem.fieldName?cap_first}());
</#list>

        return result;
    }

    private ${CapClassName}DO dto2do(${CapClassName}SaveRequest copy) {
        if (copy == null) {
            return null;
        }

        // todo
        ${CapClassName}DO result = new ${CapClassName}DO();
    <#list classInfo.fieldList as fieldItem >
        result.set${fieldItem.fieldName?cap_first}(copy.get${fieldItem.fieldName?cap_first}());
    </#list>

        return result;
    }

    public void delete(${CapClassName}DelRequest request) {
        mapper.deleteByIdList(request.getIdList());
    }

    public ${CapClassName}DTO detail(${CapClassName}DetailRequest request) {
        return do2dto(mapper.selectById(request.getId()));
    }

    public ${pageResponse}<${CapClassName}DTO> pageQuery(${pageRequest}<${CapClassName}QueryCriteria> request) {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();

        ${pageResponse}<${CapClassName}DTO> response = new ${pageResponse}<>();
        response.setPageNum(pageNum);
        response.setPageSize(pageSize);

        int total = mapper.pageListCount();
        int offset = (pageNum - 1) * pageSize;
        List<${CapClassName}DO> doList = mapper.pageList(offset, pageSize);
        List<${CapClassName}DTO> dtoList = doList.stream()
                .map(this::do2dto)
                .collect(Collectors.toList());

        response.setTotalCount(total);
        response.setDataList(dtoList);

        return response;
    }
}
