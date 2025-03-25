package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.post.PostPageReqVO;
import com.temp.biz.domain.vo.system.post.PostResVO;
import com.temp.biz.domain.vo.system.post.PostSimpleResVO;
import com.temp.biz.entity.system.SystemPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统岗位 Mapper
 *
 * @author Hollis
 * @since 2024-04-16 16:34
 */
public interface SystemPostMapper extends BaseMapper<SystemPost> {

    /**
     * 通过名称 查询岗位
     *
     * @param postName 岗位名称
     * @return {@link SystemPost }
     */
    SystemPost getByName(@Param("postName") String postName);

    /**
     * 获得岗位分页列表
     *
     * @param request 筛选条件
     * @return {@link Page }<{@link PostResVO }> 岗位信息列表
     */
    Page<PostResVO> page(Page<PostResVO> page, @Param("request") PostPageReqVO request);

    /**
     * 获取岗位详情
     *
     * @param postId 岗位Id
     * @return {@link PostResVO } 岗位详情
     */
    PostResVO getDetailById(@Param("postId") Long postId);

    /**
     * 获取岗位全列表
     * <p>
     * 只包含被开启的岗位，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link PostSimpleResVO }>
     */
    List<PostSimpleResVO> getSimplePostList();
}