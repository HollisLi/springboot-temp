package com.temp.security;

import com.trade.domain.dto.member.MemberDetailDTO;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spring Security User
 *
 * @author Hollis
 * @since 2023-09-06 11:33
 */
@Data
public class UserDetail implements UserDetails {

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 代码
     */
    private String memberCode;

    /**
     * 名称
     */
    private String memberName;

    /**
     * 密码
     */
    private String memberPassword;

    /**
     * 手机号
     */
    private String memberMobilePhone;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 企业id
     */
    private Long enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 用户角色
     */
    private Set<GrantedAuthority> grantedAuthorities;


    public UserDetail(MemberDetailDTO member, List<String> permissions) {
        this.memberId = member.getMemberId();
        this.memberCode = member.getMemberCode();
        this.memberName = member.getMemberName();
        this.memberPassword = member.getMemberPassword();
        this.memberMobilePhone = member.getMemberMobilePhone();
        this.tenantId = member.getTenantId();
        this.tenantName = member.getTenantName();
        this.enterpriseId = member.getEnterpriseId();
        this.enterpriseName = member.getEnterpriseName();
        if (CollectionUtils.isNotEmpty(permissions)) {
            this.grantedAuthorities = permissions
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.memberPassword;
    }

    @Override
    public String getUsername() {
        return this.memberCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}