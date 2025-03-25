package com.temp.biz.mapstruct.system;

import com.temp.biz.domain.vo.system.menu.MenuResVO;
import com.temp.biz.domain.vo.system.menu.MenuSaveReqVO;
import com.temp.biz.entity.system.SystemMenu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 菜单信息 Convert
 *
 * @author Hollis
 * @since 2024/06/03 下午5:57
 */
@Mapper
public interface SystemMenuConvert {

    SystemMenuConvert INSTANCE = Mappers.getMapper(SystemMenuConvert.class);

    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleteFlag", ignore = true)
    SystemMenu toEntity(MenuSaveReqVO source);

    MenuResVO toMenuResVO(SystemMenu systemMenu);
}
