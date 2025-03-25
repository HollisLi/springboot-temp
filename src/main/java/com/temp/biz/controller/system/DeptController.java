package com.temp.biz.controller.system;

import com.temp.biz.domain.vo.system.dept.DeptListReqVO;
import com.temp.biz.domain.vo.system.dept.DeptResVO;
import com.temp.biz.domain.vo.system.dept.DeptSaveReqVO;
import com.temp.biz.domain.vo.system.dept.DeptSimpleResVO;
import com.temp.biz.service.system.SystemDeptService;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 管理后台 - 部门
 *
 * @author Hollis
 * @since 2024/05/29 下午2:18
 */
@Validated
@RestController
@RequestMapping("/system/dept")
@RequiredArgsConstructor
public class DeptController {

    private final SystemDeptService systemDeptService;

    /**
     * 创建部门
     *
     * @param request 部门信息
     * @return {@link Long } 部门id
     */
    @PostMapping("/create")
    public Long create(@RequestBody @Valid DeptSaveReqVO request) {
        return systemDeptService.insert(request);
    }

    /**
     * 更新部门
     *
     * @param request 更新部门入参
     */
    @PutMapping("/update")
    public void update(@RequestBody @Valid DeptSaveReqVO request) {
        if (request.getId() == null) {
            throw new BusinessException("部门ID不能为空");
        }
        systemDeptService.update(request);
    }

    /**
     * 删除部门
     *
     * @param id 部门id
     */
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        systemDeptService.delete(id);
    }

    /**
     * 获取部门信息列表
     *
     * @param request 筛选条件
     * @return {@link List }<{@link DeptResVO }> 部门信息列表
     */
    @PostMapping("/list")
    public List<DeptResVO> list(@RequestBody DeptListReqVO request) {
        return systemDeptService.list(request);
    }

    /**
     * 获取部门精简信息列表, 只包含被开启的部门，主要用于前端的下拉选项
     */
    @GetMapping(value = {"/simple-list"})
    public List<DeptSimpleResVO> listSimpleDept(@RequestParam("enterpriseId") Long enterpriseId) {
        return systemDeptService.listSimpleDept(enterpriseId);
    }

    /**
     * 获取部门信息详情
     *
     * @param id 部门id
     * @return {@link DeptResVO } 部门信息
     */
    @GetMapping("/get")
    public DeptResVO getDetailById(@RequestParam("id") Long id) {
        return systemDeptService.getDetailById(id);
    }

}
