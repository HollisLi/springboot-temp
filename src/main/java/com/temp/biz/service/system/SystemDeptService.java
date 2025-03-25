package com.temp.biz.service.system;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.domain.vo.system.dept.DeptListReqVO;
import com.temp.biz.domain.vo.system.dept.DeptResVO;
import com.temp.biz.domain.vo.system.dept.DeptSaveReqVO;
import com.temp.biz.domain.vo.system.dept.DeptSimpleResVO;
import com.temp.biz.entity.system.SystemDept;
import com.temp.biz.mapper.system.SystemDeptMapper;
import com.temp.biz.mapstruct.system.SystemDeptConvert;
import com.temp.common.constants.ErrorMsgConstant;
import com.temp.common.enums.CommonStatusEnum;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 部门管理 Service
 *
 * @author Hollis
 * @since 2024-04-16 16:04
 */
@Service
@RequiredArgsConstructor
public class SystemDeptService extends ServiceImpl<SystemDeptMapper, SystemDept> {

    @Autowired
    @Lazy
    private SystemUsersService systemUsersService;

    /**
     * 创建部门
     *
     * @param request 部门信息
     * @return {@link Long } 部门id
     */
    public Long insert(DeptSaveReqVO request) {
        if (request.getParentId() == null) {
            request.setParentId(SystemDept.PARENT_ID_ROOT);
        }
        // 校验父部门的有效性
        verifyParentDept(null, request.getParentId(), request.getEnterpriseId());
        // 校验部门名称的唯一性
        verifyDeptNameUnique(null, request.getName(), request.getEnterpriseId());

        // 插入部门
        SystemDept entity = SystemDeptConvert.INSTANCE.toEntity(request);
        baseMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 更新部门
     *
     * @param request 部门信息
     */
    public void update(DeptSaveReqVO request) {
        if (request.getParentId() == null) {
            request.setParentId(SystemDept.PARENT_ID_ROOT);
        }
        // 验证部门是否存在
        verifyDeptExists(request.getId());
        // 校验父部门的有效性
        verifyParentDept(request.getId(), request.getParentId(), request.getEnterpriseId());
        // 校验部门名的唯一性
        verifyDeptNameUnique(request.getId(), request.getName(), request.getEnterpriseId());

        // 更新部门
        SystemDept entity = SystemDeptConvert.INSTANCE.toEntity(request);
        baseMapper.updateById(entity);
    }

    /**
     * 删除部门
     *
     * @param deptId 部门id
     */
    public void delete(Long deptId) {
        // 校验部门是否存在
        verifyDeptExists(deptId);
        // 校验是否有子部门
        Integer childDeptCount = baseMapper.countByParentId(deptId);
        if (childDeptCount > 0) {
            throw new BusinessException(ErrorMsgConstant.DEPT_EXITS_CHILDREN);
        }
        // 验证是否有员工
        Integer userCount = systemUsersService.countByDeptId(deptId);
        if (userCount > 0) {
            throw new BusinessException(ErrorMsgConstant.DEPT_EXITS_USER);
        }
        // 删除部门
        baseMapper.deleteById(deptId);
    }

    /**
     * 验证部门是否存在
     *
     * @param id ID
     */
    private void verifyDeptExists(Long id) {
        if (id == null) {
            return;
        }
        if (baseMapper.selectById(id) == null) {
            throw new BusinessException(ErrorMsgConstant.DEPT_NOT_FOUND);
        }
    }

    /**
     * 校验部门名称的唯一性
     *
     * @param deptId       部门ID
     * @param deptName     部门名称
     * @param enterpriseId 企业ID
     */
    public void verifyDeptNameUnique(Long deptId, String deptName, Long enterpriseId) {
        SystemDept systemDept = baseMapper.getByNameAndEnterpriseId(deptName, enterpriseId);
        if (systemDept == null) {
            return;
        }
        if (deptId == null || ObjectUtil.notEqual(deptId, systemDept.getId())) {
            throw new BusinessException(ErrorMsgConstant.DEPT_NAME_DUPLICATE);
        }
    }

    /**
     * 校验父部门的有效性
     *
     * @param deptId       部门ID
     * @param parentId     父部门ID
     * @param enterpriseId 企业ID
     */
    public void verifyParentDept(Long deptId, Long parentId, Long enterpriseId) {
        if (parentId == null || SystemDept.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父部门
        if (Objects.equals(deptId, parentId)) {
            throw new BusinessException(ErrorMsgConstant.DEPT_PARENT_ERROR);
        }
        // 2. 父部门必须存在
        SystemDept parentDept = baseMapper.selectById(parentId);
        if (parentDept == null) {
            throw new BusinessException(ErrorMsgConstant.DEPT_PARENT_NOT_EXITS);
        }
        // 3. 父部门和当前部门必须是同一个企业
        if (ObjectUtils.notEqual(parentDept.getEnterpriseId(), enterpriseId)) {
            throw new BusinessException(ErrorMsgConstant.DEPT_PARENT_ENTERPRISE_ERROR);
        }
        // 4. 递归校验父部门，如果父部门是自己的子部门，则报错，避免形成环路
        if (deptId == null) {
            // id 为空，说明新增，不需要考虑环路
            return;
        }
        verifyParentDeptIsChild(deptId, parentDept.getParentId());
    }

    /**
     * 验证父部门是否是当前部门的子部门
     *
     * @param deptId       当前部门id
     * @param parentDeptId 父部门id
     */
    public void verifyParentDeptIsChild(Long deptId, Long parentDeptId) {
        if (parentDeptId == null || SystemDept.PARENT_ID_ROOT.equals(parentDeptId)) {
            return;
        }
        if (Objects.equals(deptId, parentDeptId)) {
            throw new BusinessException(ErrorMsgConstant.DEPT_PARENT_IS_CHILD);
        }
        SystemDept parentDept = baseMapper.selectById(parentDeptId);
        if (parentDept == null) {
            return;
        }
        verifyParentDeptIsChild(deptId, parentDept.getParentId());
    }

    /**
     * 统计企业下的部门数量
     *
     * @param enterpriseId 企业ID
     * @return {@link Integer } 部门数量
     */
    public Integer countByEnterpriseId(Long enterpriseId) {
        return baseMapper.countByEnterpriseId(enterpriseId);
    }

    /**
     * 获取部门信息列表
     *
     * @param request 筛选条件
     * @return {@link List }<{@link DeptResVO }> 部门信息列表
     */
    public List<DeptResVO> list(DeptListReqVO request) {
        return baseMapper.list(request);
    }

    /**
     * 获取部门精简信息列表, 只包含被开启的部门，主要用于前端的下拉选项
     */
    public List<DeptSimpleResVO> listSimpleDept(Long enterpriseId) {
        DeptListReqVO queryWrapper = DeptListReqVO.builder()
                .status(CommonStatusEnum.ENABLE)
                .enterpriseId(enterpriseId)
                .build();
        List<DeptResVO> deptList = list(queryWrapper);
        return SystemDeptConvert.INSTANCE.toToSimpleList(deptList);
    }

    /**
     * 获取部门信息详情
     *
     * @param deptId 部门id
     * @return {@link DeptResVO } 部门信息
     */
    public DeptResVO getDetailById(Long deptId) {
        return baseMapper.getDetailById(deptId);
    }

    /**
     * 获取部门下的所有子部门ID
     *
     * @param deptId 部门ID
     * @return {@link List }<{@link Long }> 子部门ID列表
     */
    public List<Long> listChildIdByDeptId(Long deptId) {
        List<Long> childIds = new ArrayList<>();
        listChildIdByDeptId(childIds, ListUtil.of(deptId));
        return childIds;
    }

    /**
     * 递归获取所有子部门ID
     *
     * @param childIds  用于存储子部门ID的列表
     * @param parentIds 父部门ID列表
     */
    public void listChildIdByDeptId(List<Long> childIds, List<Long> parentIds) {
        List<Long> listChildId = baseMapper.listChildId(parentIds);
        if (CollectionUtils.isEmpty(listChildId)) {
            return;
        }
        childIds.addAll(listChildId);
        listChildIdByDeptId(childIds, listChildId);
    }
}
