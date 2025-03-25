package com.temp.biz.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.post.PostPageReqVO;
import com.temp.biz.domain.vo.system.post.PostResVO;
import com.temp.biz.domain.vo.system.post.PostSaveReqVO;
import com.temp.biz.domain.vo.system.post.PostSimpleResVO;
import com.temp.biz.service.system.SystemPostService;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 管理后台 - 岗位
 *
 * @author Hollis
 * @since 2024/06/12 下午4:46
 */
@Validated
@RestController
@RequestMapping("/system/post")
@RequiredArgsConstructor
public class PostController {

    private final SystemPostService systemPostService;

    /**
     * 新增岗位
     *
     * @param request 岗位信息
     * @return {@link Long } 岗位Id
     */
    @PostMapping("/create")
    public Long create(@RequestBody @Valid PostSaveReqVO request) {
        return systemPostService.insert(request);
    }

    /**
     * 修改岗位
     *
     * @param request 岗位Id
     */
    @PutMapping("/update")
    public void update(@RequestBody @Valid PostSaveReqVO request) {
        if (request.getId() == null) {
            throw new BusinessException("岗位ID不能为空");
        }
        systemPostService.update(request);
    }

    /**
     * 删除岗位
     *
     * @param id 岗位ID
     */
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        systemPostService.delete(id);
    }

    /**
     * 获得岗位分页列表
     *
     * @param request 筛选条件
     * @return {@link Page }<{@link PostResVO }> 岗位信息列表
     */
    @PostMapping(value = "/page")
    public Page<PostResVO> page(@RequestBody PostPageReqVO request) {
        return systemPostService.page(request);
    }

    /**
     * 获取岗位全列表
     * <p>
     * 只包含被开启的岗位，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link PostSimpleResVO }>
     */
    @GetMapping(value = {"/list-all-simple", "simple-list"})
    public List<PostSimpleResVO> getSimplePostList() {
        return systemPostService.getSimplePostList();
    }

    /**
     * 获取岗位信息
     *
     * @param id ID
     * @return {@link PostResVO }
     */
    @GetMapping(value = "/get")
    public PostResVO getDetailById(@RequestParam("id") Long id) {
        return systemPostService.getDetailById(id);
    }
}
