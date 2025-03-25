package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.temp.biz.domain.vo.system.dept.DeptListReqVO;
import com.temp.biz.domain.vo.system.dept.DeptResVO;
import com.temp.biz.entity.system.SystemDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统部门 Mapper
 *
 * @author Hollis
 * @since 2024-04-16 16:04
 */
public interface SystemDeptMapper extends BaseMapper<SystemDept> {

    /**
     * 统计企业下的部门数量
     *
     * @param enterpriseId 企业ID
     * @return {@link Integer } 部门数量
     */
    Integer countByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

    /**
     * 通过部门名称和企业ID获取部门信息
     *
     * @param name         名称
     * @param enterpriseId 企业ID
     * @return {@link SystemDept }
     */
    SystemDept getByNameAndEnterpriseId(@Param("name") String name,
                                        @Param("enterpriseId") Long enterpriseId);

    /**
     * 统计子部门数量
     *
     * @param parentId 父部门Id
     * @return {@link Integer }
     */
    Integer countByParentId(@Param("parentId") Long parentId);

    /**
     * 获取部门信息列表
     *
     * @param request 筛选条件
     * @return {@link List }<{@link DeptResVO }> 部门信息列表
     */
    List<DeptResVO> list(@Param("request") DeptListReqVO request);

    /**
     * 获取部门信息详情
     *
     * @param deptId 部门id
     * @return {@link DeptResVO } 部门信息
     */
    DeptResVO getDetailById(@Param("deptId") Long deptId);

    /**
     * 获取子部门id集合
     *
     * @param parentIds 父ID
     * @return {@link List }<{@link Long }>
     */
    List<Long> listChildId(@Param("parentIds") List<Long> parentIds);
}