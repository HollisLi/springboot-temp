package com.temp.biz.service.system;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.domain.vo.system.post.PostPageReqVO;
import com.temp.biz.domain.vo.system.post.PostResVO;
import com.temp.biz.domain.vo.system.post.PostSaveReqVO;
import com.temp.biz.domain.vo.system.post.PostSimpleResVO;
import com.temp.biz.entity.system.SystemPost;
import com.temp.biz.mapper.system.SystemPostMapper;
import com.temp.biz.mapstruct.system.SystemPostConvert;
import com.temp.common.constants.ErrorMsgConstant;
import com.temp.framework.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 系统岗位 Service
 *
 * @author Hollis
 * @since 2024-04-16 16:34
 */
@Service
public class SystemPostService extends ServiceImpl<SystemPostMapper, SystemPost> {

    /**
     * 新增岗位
     *
     * @param request 岗位信息
     * @return {@link Long } 岗位ID
     */
    public Long insert(PostSaveReqVO request) {
        // 验证岗位名称唯一性
        verifyPostNameUnique(null, request.getName());

        // 新增岗位
        SystemPost post = SystemPostConvert.INSTANCE.toEntity(request);
        baseMapper.insert(post);
        return post.getId();
    }

    /**
     * 验证岗位名称唯一
     *
     * @param postId   岗位ID
     * @param postName 岗位名称
     */
    private void verifyPostNameUnique(Long postId, String postName) {
        SystemPost systemPost = baseMapper.getByName(postName);
        if (systemPost == null) {
            return;
        }
        if (postId == null || ObjectUtil.notEqual(postId, systemPost.getId())) {
            throw new BusinessException(ErrorMsgConstant.POST_NAME_DUPLICATE);
        }
    }

    /**
     * 修改岗位
     *
     * @param request 岗位信息
     */
    public void update(PostSaveReqVO request) {
        // 验证岗位名称唯一性
        verifyPostNameUnique(request.getId(), request.getName());

        // 修改岗位
        SystemPost post = SystemPostConvert.INSTANCE.toEntity(request);
        baseMapper.updateById(post);
    }

    /**
     * 删除岗位
     *
     * @param id 岗位ID
     */
    public void delete(Long id) {
        // 验证岗位是否存在
        validatePostExists(id);
        // 删除岗位
        baseMapper.deleteById(id);
    }

    /**
     * 获得岗位分页列表
     *
     * @param request 筛选条件
     * @return {@link Page }<{@link PostResVO }> 岗位信息列表
     */
    public Page<PostResVO> page(@RequestBody PostPageReqVO request) {
        Page<PostResVO> page = new Page<>(request.getPageNum(), request.getPageSize());
        return baseMapper.page(page, request);
    }

    /**
     * 验证岗位是否存在, 不存在抛出异常
     *
     * @param id 岗位ID
     */
    private void validatePostExists(Long id) {
        if (id == null) {
            return;
        }
        if (baseMapper.selectById(id) == null) {
            throw new BusinessException(ErrorMsgConstant.POST_NOT_FOUND);
        }
    }

    /**
     * 获取岗位详情
     *
     * @param postId 岗位Id
     * @return {@link PostResVO } 岗位详情
     */
    public PostResVO getDetailById(Long postId) {
        return baseMapper.getDetailById(postId);
    }

    /**
     * 获取岗位全列表
     * <p>
     * 只包含被开启的岗位，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link PostSimpleResVO }>
     */
    public List<PostSimpleResVO> getSimplePostList() {
        return baseMapper.getSimplePostList();
    }
}
