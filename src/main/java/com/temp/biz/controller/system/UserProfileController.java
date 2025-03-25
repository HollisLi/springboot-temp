package com.temp.biz.controller.system;

import com.temp.biz.domain.vo.system.user.UserProfileRespVO;
import com.temp.biz.service.system.SystemUsersService;
import com.temp.framework.security.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理后台 - 用户个人中心
 *
 * @author Hollis
 * @since 2024/06/18 下午2:08
 */
@Validated
@RestController
@RequestMapping("/system/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final SystemUsersService systemUsersService;

    /**
     * 获得登录用户信息
     *
     * @return {@link UserProfileRespVO }
     */
    @GetMapping("/get")
    public UserProfileRespVO getUserProfile() {
        return systemUsersService.getUserProfile(TokenHolder.getUserId());
    }
}
