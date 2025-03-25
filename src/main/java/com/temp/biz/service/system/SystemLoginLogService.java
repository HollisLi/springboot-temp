package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.domain.vo.system.logger.LoginLogPageReqVO;
import com.temp.biz.domain.vo.system.logger.LoginLogRespVO;
import com.temp.biz.entity.system.SystemLoginLog;
import com.temp.biz.mapper.system.SystemLoginLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统访问记录 Service
 *
 * @author Hollis
 * @since 2024-06-17 15:28
 */
@Service
public class SystemLoginLogService extends ServiceImpl<SystemLoginLogMapper, SystemLoginLog> {


    /**
     * 获得登录日志分页列表
     *
     * @param request 筛选条件
     * @return {@link Page <LoginLogRespVO> } 登录日志分页列表
     */
    @PostMapping("/page")
    public Page<LoginLogRespVO> page(@RequestBody LoginLogPageReqVO request) {
        Page<LoginLogRespVO> page = new Page<>(request.getPageNum(), request.getPageSize());
        return baseMapper.page(page, request);
    }

}
