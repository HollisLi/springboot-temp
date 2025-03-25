package com.temp.biz.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.enterprise.EnterprisePageReqVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseResVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseSaveReqVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseSimpleResVO;
import com.temp.biz.service.system.SystemEnterpriseService;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 管理后台 - 企业
 *
 * @author Hollis
 * @since 2024/06/03 下午1:52
 */
@Validated
@RestController
@RequestMapping("/system/enterprise")
@RequiredArgsConstructor
public class EnterpriseController {

    private final SystemEnterpriseService systemEnterpriseService;

    /**
     * 新增企业
     *
     * @param request 企业信息
     * @return {@link Long } 企业id
     */
    @PostMapping("/create")
    public Long create(@RequestBody @Valid EnterpriseSaveReqVO request) {
        return systemEnterpriseService.insert(request);
    }

    /**
     * 修改企业
     *
     * @param request 企业信息
     */
    @PutMapping("/update")
    public void update(@RequestBody @Valid EnterpriseSaveReqVO request) {
        if (request.getId() == null) {
            throw new BusinessException("企业ID不能为空");
        }
        systemEnterpriseService.update(request);
    }

    /**
     * 删除企业
     *
     * @param id 企业Id
     */
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        systemEnterpriseService.delete(id);
    }

    /**
     * 分页查询企业列表
     *
     * @param request 筛选条件
     * @return {@link Page }<{@link EnterpriseResVO }> 企业信息列表
     */
    @PostMapping("/page")
    public Page<EnterpriseResVO> page(@RequestBody EnterprisePageReqVO request) {
        return systemEnterpriseService.page(request);
    }

    /**
     * 获取企业精简信息列表, 只包含被开启的企业，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link EnterpriseSimpleResVO }> 精简企业信息
     */
    @PostMapping("/simple-list")
    public List<EnterpriseSimpleResVO> simpleList() {
        return systemEnterpriseService.simpleList();
    }

    /**
     * 查询企业详情
     *
     * @param id 企业Id
     */
    @GetMapping("/get")
    public EnterpriseResVO getDetailById(@RequestParam("id") Long id) {
        return systemEnterpriseService.getDetailById(id);
    }
}
