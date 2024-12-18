package com.temp.biz.controller.system;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.temp.biz.domain.vo.system.auth.AuthPermissionInfoRespVO;
import com.temp.biz.domain.vo.system.auth.ChangePasswordReqVO;
import com.temp.biz.domain.vo.system.auth.LoginReqVO;
import com.temp.biz.service.system.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 身份验证 Controller
 *
 * @author Hollis
 * @since 2024-04-11 15:43
 */
@Validated
@RestController
@RequestMapping("/system/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 登录
     *
     * @param loginReq 账号密码
     * @return {@link SaTokenInfo} Token 信息
     */
    @PostMapping("/login")
    public SaTokenInfo login(@RequestBody @Valid LoginReqVO loginReq) {
        return authService.login(loginReq);
    }

    /**
     * 注销
     *
     * @param request 请求头
     */
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        authService.logout(request);
    }

    /**
     * 修改密码
     *
     * @param req 新密码
     */
    @PostMapping("/change/password")
    public void changePassword(HttpServletRequest request, @RequestBody @Valid ChangePasswordReqVO req) {
        authService.changePassword(request, req);
    }

    /**
     * 获取登录用户的权限信息
     *
     * @return {@link AuthPermissionInfoRespVO }
     */
    @GetMapping("/get-permission-info")
    public AuthPermissionInfoRespVO getPermissionInfo() {
        return authService.getPermissionInfo();
    }

}

