package com.temp.biz.mapstruct.system;

import com.temp.biz.domain.vo.system.menu.MenuVO;
import com.temp.biz.entity.system.SystemMenu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 鉴权 Convert
 *
 * @author Hollis
 * @since 2024/06/18 下午4:07
 */
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    @Mapping(target = "permission", source = "permission")
    @Mapping(target = "sort", source = "sort")
    @Mapping(target = "children", ignore = true)
    MenuVO toMenuVO(SystemMenu menu);
}
