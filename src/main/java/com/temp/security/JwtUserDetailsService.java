package com.temp.security;

import com.trade.domain.dto.member.MemberDetailDTO;
import com.trade.enums.DataStatusEnum;
import com.trade.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Spring Security UserDetailsService impl
 *
 * @author Hollis
 * @since 2023-09-06 11:21
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

    private static final List<String> DEF_MEMBER_ROLE = List.of("ROLE_MEMBER");

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        // 查询用户信息
        MemberDetailDTO member = memberService.queryMemberDetailByMemberCode(username);
        if (Objects.isNull(member)) {
            throw new UsernameNotFoundException("未查询到会员信息!");
        }

        // 校验用户状态
        DataStatusEnum memberStatus = member.getDataStatus();
        if (DataStatusEnum.INVALID.equals(memberStatus)) {
            throw new DisabledException("该账号已被禁用, 请联系管理员处理!");
        }
        return new UserDetail(member, DEF_MEMBER_ROLE);
    }
}