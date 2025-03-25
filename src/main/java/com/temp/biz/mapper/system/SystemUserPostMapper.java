package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.temp.biz.entity.system.SystemPost;
import com.temp.biz.entity.system.SystemUserPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户岗位 Mapper
 *
 * @author Hollis
 * @since 2024-04-16 16:35
 */
public interface SystemUserPostMapper extends BaseMapper<SystemUserPost> {

    /**
     * 查询用户所属的岗位
     *
     * @param userId 用户ID
     * @return {@link SystemPost}
     */
    List<SystemPost> getPostByUserId(@Param("userId") Long userId);

    /**
     * 删除用户岗位关联
     *
     * @param userId 用户ID
     */
    void deleteByUserId(@Param("userId") Long userId);
}