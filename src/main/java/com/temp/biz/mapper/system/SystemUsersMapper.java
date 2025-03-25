package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.UserInfoResVO;
import com.temp.biz.domain.vo.system.online.OnlinePageReqVO;
import com.temp.biz.domain.vo.system.online.OnlineResVO;
import com.temp.biz.domain.vo.system.post.PostResVO;
import com.temp.biz.domain.vo.system.user.UserPageReqVO;
import com.temp.biz.domain.vo.system.user.UserResVO;
import com.temp.biz.domain.vo.system.user.UserSimpleRespVO;
import com.temp.biz.entity.system.SystemUsers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表 Mapper
 *
 * @author Hollis
 * @since 2024-04-11 17:13
 */
public interface SystemUsersMapper extends BaseMapper<SystemUsers> {

    /**
     * 通过用户名查询未删除的用户
     *
     * @param username 用户名
     * @return {@link SystemUsers }
     */
    SystemUsers getByUsername(@Param("username") String username);

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return {@link UserInfoResVO}
     */
    UserInfoResVO getUserInfo(@Param("userId") Long userId);

    /**
     * 统计企业下的用户数量
     *
     * @param enterpriseId 企业ID
     * @return {@link Integer } 用户数量
     */
    Integer countByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

    /**
     * 统计部门下的用户数量
     *
     * @param deptId 部门ID
     * @return {@link Integer } 用户数量
     */
    Integer countByDeptId(@Param("deptId") Long deptId);

    /**
     * 通过企业id 和 登录账号查询用户
     *
     * @param username     用户名
     * @param enterpriseId 企业ID
     * @return {@link SystemUsers }
     */
    SystemUsers getByUsernameAndEnterpriseId(@Param("username") String username,
                                             @Param("enterpriseId") Long enterpriseId);

    /**
     * 分页查询用户信息
     *
     * @param pageReqVO 分页和筛选条件
     * @return {@link Page }<{@link UserResVO }> 用户信息
     */
    Page<UserResVO> page(Page<UserResVO> page, @Param("pageReqVO") UserPageReqVO pageReqVO);

    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return {@link UserResVO }
     */
    UserResVO getDetailById(@Param("userId") Long userId);

    /**
     * 只包含被开启的用户，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link UserSimpleRespVO }>
     */
    List<UserSimpleRespVO> getSimpleUserList();

    /**
     * 获得在线用户分页列表
     *
     * @param request 筛选条件
     * @return {@link Page }<{@link PostResVO }> 在线用户列表
     */
    Page<OnlineResVO> pageOnline(Page<OnlineResVO> page, @Param("req") OnlinePageReqVO request);
}