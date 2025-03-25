package com.temp.biz.mapstruct.system;

import com.temp.biz.domain.vo.system.user.UserProfileRespVO;
import com.temp.biz.domain.vo.system.user.UserSaveReqVO;
import com.temp.biz.domain.vo.system.user.UserVO;
import com.temp.biz.entity.system.SystemUsers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 用户信息 Convert
 *
 * @author Hollis
 * @since 2024/06/03 下午5:57
 */
@Mapper
public interface SystemUserConvert {

    SystemUserConvert INSTANCE = Mappers.getMapper(SystemUserConvert.class);

    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleteFlag", ignore = true)
    @Mapping(target = "loginIp", ignore = true)
    @Mapping(target = "loginDate", ignore = true)
    SystemUsers toEntity(UserSaveReqVO source);

    UserVO toUserVO(SystemUsers users);

    @Mapping(target = "enterprise", ignore = true)
    @Mapping(target = "dept", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserProfileRespVO toUserProfileRespVO(SystemUsers userInfo);
}
