package com.temp.biz.mapstruct.system;

import com.temp.biz.domain.vo.system.role.RoleDetailResVO;
import com.temp.biz.domain.vo.system.role.RoleResVO;
import com.temp.biz.domain.vo.system.role.RoleSaveReqVO;
import com.temp.biz.domain.vo.system.role.RoleSimpleResVO;
import com.temp.biz.entity.system.SystemRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * 角色信息 Convert
 *
 * @author Hollis
 * @since 2024/06/03 下午5:57
 */
@Mapper
public interface SystemRoleConvert {

    SystemRoleConvert INSTANCE = Mappers.getMapper(SystemRoleConvert.class);

    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleteFlag", ignore = true)
    SystemRole toEntity(RoleSaveReqVO source);

    @Mapping(target = "menuIds", ignore = true)
    RoleDetailResVO toDetailRes(RoleResVO source);

    List<RoleSimpleResVO> toRoleSimpleResVOList(Set<SystemRole> systemRoles);
}
