package com.temp.biz.mapstruct.system;

import com.temp.biz.domain.vo.system.dept.DeptResVO;
import com.temp.biz.domain.vo.system.dept.DeptSaveReqVO;
import com.temp.biz.domain.vo.system.dept.DeptSimpleResVO;
import com.temp.biz.entity.system.SystemDept;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 部门信息 Convert
 *
 * @author Hollis
 * @since 2024/06/03 下午5:57
 */
@Mapper
public interface SystemDeptConvert {

    SystemDeptConvert INSTANCE = Mappers.getMapper(SystemDeptConvert.class);

    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleteFlag", ignore = true)
    SystemDept toEntity(DeptSaveReqVO source);

    List<DeptSimpleResVO> toToSimpleList(List<DeptResVO> source);

    DeptSimpleResVO toDeptSimpleResVO(DeptResVO dept);
}
