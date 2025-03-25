package com.temp.biz.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.online.OnlinePageReqVO;
import com.temp.biz.domain.vo.system.online.OnlineResVO;
import com.temp.biz.domain.vo.system.post.PostResVO;
import com.temp.biz.service.system.AuthService;
import com.temp.biz.service.system.SystemUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理后台 - 在线用户
 *
 * @author Hollis
 * @since 2024/06/19 下午3:48
 */
@Validated
@RestController
@RequestMapping("/system/online")
@RequiredArgsConstructor
public class OnlineController {

    private final AuthService authService;
    private final SystemUsersService systemUsersService;

    /**
     * 获得在线用户分页列表
     *
     * @param request 筛选条件
     * @return {@link Page }<{@link PostResVO }> 在线用户列表
     */
    @PostMapping(value = "/page")
    public Page<OnlineResVO> page(@RequestBody OnlinePageReqVO request) {
        return systemUsersService.pageOnline(request);
    }

    /**
     * 强制登出
     *
     * @param userId 用户Id
     */
    @GetMapping(value = "/logout-forced")
    public void forcedLogout(@RequestParam("userId") Long userId) {
        authService.forcedLogout(userId);
    }
}
