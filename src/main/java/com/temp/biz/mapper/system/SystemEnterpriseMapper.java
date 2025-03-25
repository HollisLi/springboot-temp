package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.enterprise.EnterprisePageReqVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseResVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseSimpleResVO;
import com.temp.biz.entity.system.SystemEnterprise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业信息表 Mapper
 *
 * @author Hollis
 * @since 2024-04-16 14:17
 */
public interface SystemEnterpriseMapper extends BaseMapper<SystemEnterprise> {

    /**
     * 根据企业名称查询企业信息
     *
     * @param enterpriseName 企业名称
     * @return {@link SystemEnterprise }
     */
    SystemEnterprise getByEnterpriseName(@Param("enterpriseName") String enterpriseName);

    /**
     * 查询企业信息列表
     *
     * @param request 查询参数
     * @return {@link Page }<{@link EnterpriseResVO }> 企业信息列表
     */
    Page<EnterpriseResVO> page(Page<EnterpriseResVO> page, @Param("request") EnterprisePageReqVO request);

    /**
     * 查询企业信息
     *
     * @param enterpriseId 企业ID
     * @return {@link EnterpriseResVO } 企业信息
     */
    EnterpriseResVO getDetailById(@Param("enterpriseId") Long enterpriseId);

    /**
     * 获取企业精简信息列表, 只包含被开启的企业，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link EnterpriseSimpleResVO }>
     */
    List<EnterpriseSimpleResVO> listSimple();
}