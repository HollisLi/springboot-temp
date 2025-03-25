package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.entity.system.SystemPost;
import com.temp.biz.entity.system.SystemUserPost;
import com.temp.biz.mapper.system.SystemUserPostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统用户岗位服务
 *
 * @author Hollis
 * @since 2024-04-16 16:35
 */
@Service
public class SystemUserPostService extends ServiceImpl<SystemUserPostMapper, SystemUserPost> {

    /**
     * 查询用户岗位
     *
     * @param userId 用户ID
     * @return {@link SystemPost }
     */
    public List<SystemPost> getPostByUserId(Long userId) {
        return baseMapper.getPostByUserId(userId);
    }

    /**
     * 删除用户岗位关联
     *
     * @param userId 用户ID
     */
    public void deleteByUserId(Long userId) {
        baseMapper.deleteByUserId(userId);
    }
}
