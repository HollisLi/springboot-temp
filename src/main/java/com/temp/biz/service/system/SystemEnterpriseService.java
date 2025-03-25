package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.domain.vo.system.enterprise.EnterprisePageReqVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseResVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseSaveReqVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseSimpleResVO;
import com.temp.biz.entity.system.SystemEnterprise;
import com.temp.biz.mapper.system.SystemEnterpriseMapper;
import com.temp.biz.mapstruct.system.SystemEnterpriseConvert;
import com.temp.common.constants.ErrorMsgConstant;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业管理 Service
 *
 * @author Hollis
 * @since 2024-04-16 14:17
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class SystemEnterpriseService extends ServiceImpl<SystemEnterpriseMapper, SystemEnterprise> {

    private final SystemDeptService systemDeptService;

    @Autowired
    @Lazy
    private SystemUsersService systemUsersService;

    /**
     * 新增企业
     *
     * @param request 企业 新增 参数
     * @return {@link Long } 企业id
     */
    public Long insert(EnterpriseSaveReqVO request) {
        // 验证企业名称唯一性
        verifyEnterpriseNameUnique(null, request.getEnterpriseName());

        // 新增企业
        SystemEnterprise entity = SystemEnterpriseConvert.INSTANCE.toEntity(request);
        baseMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 修改企业
     *
     * @param request 企业 更新 参数
     */
    public void update(EnterpriseSaveReqVO request) {
        // 校验企业自身存在
        verifyEnterpriseExists(request.getId());
        // 验证企业名称唯一性
        verifyEnterpriseNameUnique(request.getId(), request.getEnterpriseName());

        // 更新企业
        SystemEnterprise entity = SystemEnterpriseConvert.INSTANCE.toEntity(request);
        baseMapper.updateById(entity);
    }

    /**
     * 删除企业
     *
     * @param enterpriseId 企业ID
     */
    public void delete(Long enterpriseId) {
        // 校验企业自身存在
        verifyEnterpriseExists(enterpriseId);
        // 校验企业下是否有员工
        Integer userCount = systemUsersService.countByEnterpriseId(enterpriseId);
        if (userCount > 0) {
            throw new BusinessException(ErrorMsgConstant.ENTERPRISE_HAS_USER);
        }
        // 校验企业下是否有部门
        Integer deptCount = systemDeptService.countByEnterpriseId(enterpriseId);
        if (deptCount > 0) {
            throw new BusinessException(ErrorMsgConstant.ENTERPRISE_HAS_DEPT);
        }
        // 删除企业
        baseMapper.deleteById(enterpriseId);
    }

    /**
     * 通过企业Id 验证企业是否存在
     *
     * @param enterpriseId ID 企业ID
     */
    public void verifyEnterpriseExists(Long enterpriseId) {
        if (enterpriseId == null) {
            return;
        }
        if (baseMapper.selectById(enterpriseId) == null) {
            throw new BusinessException(ErrorMsgConstant.ENTERPRISE_NOT_FOUND);
        }
    }

    /**
     * 验证企业名称唯一性
     *
     * @param enterpriseId   企业ID
     * @param enterpriseName 企业名称
     */
    public void verifyEnterpriseNameUnique(Long enterpriseId, String enterpriseName) {
        SystemEnterprise systemEnterprise = baseMapper.getByEnterpriseName(enterpriseName);
        if (systemEnterprise == null) {
            return;
        }
        if (enterpriseId == null || ObjectUtils.notEqual(enterpriseId, systemEnterprise.getId())) {
            throw new BusinessException(ErrorMsgConstant.ENTERPRISE_NAME_DUPLICATE);
        }
    }

    /**
     * 查询企业信息列表
     *
     * @param request 企业信息列表查询参数
     * @return {@link Page }<{@link EnterpriseResVO }> 企业信息列表
     */
    public Page<EnterpriseResVO> page(EnterprisePageReqVO request) {
        Page<EnterpriseResVO> page = new Page<>(request.getPageNum(), request.getPageSize());
        return baseMapper.page(page, request);
    }

    /**
     * 获取企业精简信息列表, 只包含被开启的企业，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link EnterpriseSimpleResVO }> 精简企业信息
     */
    public List<EnterpriseSimpleResVO> simpleList() {
        return baseMapper.listSimple();
    }

    /**
     * 查询企业信息
     *
     * @param enterpriseId 企业ID
     * @return {@link EnterpriseResVO } 企业信息
     */
    public EnterpriseResVO getDetailById(Long enterpriseId) {
        return baseMapper.getDetailById(enterpriseId);
    }
}
