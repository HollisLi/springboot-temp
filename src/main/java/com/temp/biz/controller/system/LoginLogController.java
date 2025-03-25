package com.temp.biz.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.logger.LoginLogPageReqVO;
import com.temp.biz.domain.vo.system.logger.LoginLogRespVO;
import com.temp.biz.service.system.SystemLoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理后台 - 登录日志
 *
 * @author Hollis
 * @since 2024/06/17 下午3:04
 */
@Validated
@RestController
@RequestMapping("/system/login-log")
@RequiredArgsConstructor
public class LoginLogController {

    private final SystemLoginLogService systemLoginLogService;

    /**
     * 获得登录日志分页列表
     *
     * @param request 筛选条件
     * @return {@link Page <LoginLogRespVO> } 登录日志分页列表
     */
    @PostMapping("/page")
    public Page<LoginLogRespVO> page(@RequestBody LoginLogPageReqVO request) {
        return systemLoginLogService.page(request);
    }
}
