package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.temp.biz.domain.vo.system.menu.MenuListReqVO;
import com.temp.biz.domain.vo.system.menu.MenuResVO;
import com.temp.biz.domain.vo.system.menu.MenuSimpleRespVO;
import com.temp.biz.entity.system.SystemMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单权限表 Mapper
 *
 * @author Hollis
 * @since 2024-06-13 15:24
 */
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    /**
     * 通过父ID和名称，获得菜单
     *
     * @param parentId 父母亲ID
     * @param name     名称
     * @return {@link SystemMenu }
     */
    SystemMenu selectByParentIdAndName(@Param("parentId") Long parentId, @Param("name") String name);

    /**
     * 统计父菜单下的子菜单数量
     *
     * @param parentId 父菜单ID
     * @return int 子菜单数量
     */
    int countByParentId(@Param("parentId") Long parentId);

    /**
     * 获取菜单列表
     *
     * @param request 筛选条件
     * @return {@link List }<{@link MenuResVO }> 菜单列表信息
     */
    List<MenuResVO> list(@Param("request") MenuListReqVO request);

    /**
     * 获取菜单精简信息列表
     * <p>
     * 只包含被开启的菜单，用于【角色分配菜单】功能的选项。
     *
     * @return {@link List }<{@link MenuSimpleRespVO }> 精简菜单列表信息
     */
    List<MenuSimpleRespVO> listSimple();

}