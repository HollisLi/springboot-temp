package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.logger.LoginLogPageReqVO;
import com.temp.biz.domain.vo.system.logger.LoginLogRespVO;
import com.temp.biz.entity.system.SystemLoginLog;
import org.apache.ibatis.annotations.Param;

/**
 * 系统访问记录 Mapper
 *
 * @author Hollis
 * @since 2024-06-17 15:28
 */
public interface SystemLoginLogMapper extends BaseMapper<SystemLoginLog> {

    /**
     * 获得登录日志分页列表
     *
     * @param request 筛选条件
     * @return {@link Page <LoginLogRespVO> } 登录日志分页列表
     */
    Page<LoginLogRespVO> page(Page<LoginLogRespVO> page, @Param("request") LoginLogPageReqVO request);
}