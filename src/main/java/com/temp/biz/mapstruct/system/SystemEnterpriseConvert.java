package com.temp.biz.mapstruct.system;

import com.temp.biz.domain.vo.system.enterprise.EnterpriseSaveReqVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseSimpleResVO;
import com.temp.biz.entity.system.SystemEnterprise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 企业信息 Convert
 *
 * @author Hollis
 * @since 2024/06/03 下午2:30
 */
@Mapper
public interface SystemEnterpriseConvert {

    SystemEnterpriseConvert INSTANCE = Mappers.getMapper(SystemEnterpriseConvert.class);

    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleteFlag", ignore = true)
    SystemEnterprise toEntity(EnterpriseSaveReqVO enterpriseVO);

    EnterpriseSimpleResVO toEnterpriseSimpleResVO(SystemEnterprise enterprise);
}
