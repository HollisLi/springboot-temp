package com.temp.biz.mapstruct.system;

import com.temp.biz.domain.vo.system.post.PostSaveReqVO;
import com.temp.biz.domain.vo.system.post.PostSimpleResVO;
import com.temp.biz.entity.system.SystemPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 岗位信息 Convert
 *
 * @author Hollis
 * @since 2024/06/03 下午5:57
 */
@Mapper
public interface SystemPostConvert {

    SystemPostConvert INSTANCE = Mappers.getMapper(SystemPostConvert.class);

    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleteFlag", ignore = true)
    SystemPost toEntity(PostSaveReqVO source);

    List<PostSimpleResVO> toSystemPostList(List<SystemPost> posts);
}
