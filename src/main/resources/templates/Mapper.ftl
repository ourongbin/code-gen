package ${packageName};

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface ${CapClassName}Mapper {

    int insert(${CapClassName}DO ${className});

    int deleteByIdList(@Param("idList") List<Long> idList);

    int updateById(${CapClassName}DO ${CapClassName});

    ${CapClassName}DO selectById(long id);

    List<${CapClassName}DO> pageList(int offset, int limit);

    int pageListCount();

}
