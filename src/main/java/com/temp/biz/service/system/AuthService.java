package com.temp.biz.service.system;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.temp.biz.domain.vo.system.auth.ChangePasswordReqVO;
import com.temp.biz.domain.vo.system.auth.LoginReqVO;
import com.temp.biz.entity.system.SystemUser;
import com.temp.biz.mapper.system.SystemUserMapper;
import com.temp.framework.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 身份验证 Service
 *
 * @author Hollis
 * @since 2024/12/17 11:20
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    private final SystemUserMapper systemUserMapper;

    @Value("${secure.key}")
    private String secureKey;

    /**
     * 登录
     *
     * @param loginReq 账号密码
     * @return {@link SaTokenInfo } Token 信息
     */
    public SaTokenInfo login(LoginReqVO loginReq) {
        SystemUser user = systemUserMapper.getByUsername(loginReq.getUsername());
        if (user == null) {
            throw new BusinessException("账号不存在");
        }
        if (Boolean.TRUE.equals(user.getStatus())) {
            throw new BusinessException("账号已被禁用");
        }
        // AES全称高级加密标准（英语：Advanced Encryption Standard，缩写：AES），在密码学中又称Rijndael加密法。
        AES aes = SecureUtil.aes(secureKey.getBytes());
        if (!user.getPassword().equals(aes.encryptHex(loginReq.getPassword()))) {
            throw new BusinessException("密码错误");
        }
        StpUtil.login(user.getId());
        new SaSession().set("user", user);
        return StpUtil.getTokenInfo();
    }

    /**
     * 注销登录
     *
     * @param request 请求
     */
    public void logout(HttpServletRequest request) {
        StpUtil.logout();
    }

    public void changePassword(HttpServletRequest request, @Valid ChangePasswordReqVO req) {
        request.
    }
}
