package com.temp.security.filter;

import com.trade.security.JwtUserDetailsService;
import com.trade.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 登录过滤器
 *
 * @author Hollis
 * @since 2024-01-08 16:12
 */
@Log4j2
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String TOKEN_HEAD = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        // 从请求头中获取 Token 信息
        final String token = request.getHeader(AUTHORIZATION);

        // 校验 Token 有效性
        if (StringUtils.isBlank(token) || !token.startsWith(TOKEN_HEAD)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        String jwtToken = token.replace(TOKEN_HEAD, StringUtils.EMPTY);

        try {
            username = jwtTokenUtil.extractUsername(jwtToken);
        } catch (IllegalArgumentException e) {
            log.warn("JwtRequestFilter#doFilterInternal Unable to get JWT Token, jwtToken : {}", jwtToken, e);
        } catch (ExpiredJwtException e) {
            log.warn("JwtRequestFilter#doFilterInternal JWT Token has expired, jwtToken : {}", jwtToken, e);
        }

        if (StringUtils.isBlank(username)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Once we get the token validate it.
        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

        // if token is valid configure Spring Security to manually set authentication
        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}