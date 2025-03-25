package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.domain.vo.system.menu.MenuListReqVO;
import com.temp.biz.domain.vo.system.menu.MenuResVO;
import com.temp.biz.domain.vo.system.menu.MenuSaveReqVO;
import com.temp.biz.domain.vo.system.menu.MenuSimpleRespVO;
import com.temp.biz.entity.system.SystemMenu;
import com.temp.biz.mapper.system.SystemMenuMapper;
import com.temp.biz.mapstruct.system.SystemMenuConvert;
import com.temp.common.constants.ErrorMsgConstant;
import com.temp.common.enums.MenuTypeEnum;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单权限表 Service
 *
 * @author Hollis
 * @since 2024-06-13 15:24
 */
@Service
@RequiredArgsConstructor
public class SystemMenuService extends ServiceImpl<SystemMenuMapper, SystemMenu> {

    private final SystemRoleMenuService systemRoleMenuService;

    /**
     * 新增菜单
     *
     * @param request 创造请求vo
     * @return {@link Long }
     */
    public Long insert(MenuSaveReqVO request) {
        // 校验父菜单
        validateParentMenu(request.getParentId(), null);
        // 校验菜单（自己）
        validateMenu(request.getParentId(), request.getName(), null);

        // 插入数据库
        SystemMenu menu = SystemMenuConvert.INSTANCE.toEntity(request);
        initMenuProperty(menu);
        baseMapper.insert(menu);
        return menu.getId();
    }

    /**
     * 修改菜单
     *
     * @param request 菜单信息
     */
    public void update(MenuSaveReqVO request) {
        // 校验更新的菜单是否存在
        validateMenuExits(request.getId());
        // 校验父菜单存在
        validateParentMenu(request.getParentId(), request.getId());
        // 校验菜单（自己）
        validateMenu(request.getParentId(), request.getName(), request.getId());

        // 更新到数据库
        SystemMenu menu = SystemMenuConvert.INSTANCE.toEntity(request);
        initMenuProperty(menu);
        baseMapper.updateById(menu);
    }

    /**
     * 初始化菜单的通用属性。
     * <p>
     * 例如说，只有目录或者菜单类型的菜单，才设置 icon
     *
     * @param menu 菜单
     */
    private void initMenuProperty(SystemMenu menu) {
        // 菜单为按钮类型时，无需 component、icon、path 属性，进行置空
        if (MenuTypeEnum.BUTTON.equals(menu.getType())) {
            menu.setComponent("");
            menu.setComponentName("");
            menu.setIcon("");
            menu.setPath("");
        }
    }

    /**
     * 校验菜单是否合法
     * <p>
     * 校验相同父菜单编号下，是否存在相同的菜单名
     *
     * @param name     菜单名字
     * @param parentId 父菜单编号
     * @param childId  菜单编号
     */
    private void validateMenu(Long parentId, String name, Long childId) {
        SystemMenu menu = baseMapper.selectByParentIdAndName(parentId, name);
        if (menu == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的菜单
        if (childId == null || ObjectUtils.notEqual(menu.getId(), childId)) {
            throw new BusinessException(ErrorMsgConstant.MENU_NAME_DUPLICATE);
        }
    }

    /**
     * 校验父菜单是否合法
     * <p>
     * 1. 不能设置自己为父菜单
     * 2. 父菜单不存在
     * 3. 父菜单必须是 {@link MenuTypeEnum#MENU} 菜单类型
     *
     * @param parentId 父菜单编号
     * @param childId  当前菜单编号
     */
    private void validateParentMenu(Long parentId, Long childId) {
        if (parentId == null || SystemMenu.ID_ROOT.equals(parentId)) {
            return;
        }
        // 不能设置自己为父菜单
        if (parentId.equals(childId)) {
            throw new BusinessException(ErrorMsgConstant.MENU_PARENT_ERROR);
        }

        SystemMenu menu = baseMapper.selectById(parentId);
        // 父菜单不存在
        if (menu == null) {
            throw new BusinessException(ErrorMsgConstant.MENU_PARENT_NOT_EXISTS);
        }
        // 父菜单必须是目录或者菜单类型
        if (!MenuTypeEnum.DIR.equals(menu.getType())
                && !MenuTypeEnum.MENU.equals(menu.getType())) {
            throw new BusinessException(ErrorMsgConstant.MENU_PARENT_NOT_DIR_OR_MENU);
        }
    }

    /**
     * 验证菜单是否存在
     *
     * @param id ID
     */
    private void validateMenuExits(Long id) {
        if (baseMapper.selectById(id) == null) {
            throw new BusinessException(ErrorMsgConstant.MENU_NOT_EXISTS);
        }
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long menuId) {
        // 校验删除的菜单是否存在
        validateMenuExits(menuId);
        // 校验是否还有子菜单
        if (baseMapper.countByParentId(menuId) > 0) {
            throw new BusinessException(ErrorMsgConstant.MENU_EXISTS_CHILDREN);
        }
        // 删除授予给角色的权限
        systemRoleMenuService.deleteByMenuId(menuId);
        // 标记删除
        baseMapper.deleteById(menuId);
    }

    /**
     * 获取菜单列表
     *
     * @param request 筛选条件
     * @return {@link List }<{@link MenuResVO }> 菜单列表信息
     */
    public List<MenuResVO> list(MenuListReqVO request) {
        return baseMapper.list(request);
    }

    /**
     * 获取菜单精简信息列表
     * <p>
     * 只包含被开启的菜单，用于【角色分配菜单】功能的选项。
     *
     * @return {@link List }<{@link MenuSimpleRespVO }> 精简菜单列表信息
     */
    public List<MenuSimpleRespVO> simpleList() {
        return baseMapper.listSimple();
    }

    /**
     * 查询菜单详情
     *
     * @param menuId 菜单ID
     * @return {@link MenuResVO } 获取菜单详情
     */
    public MenuResVO getDetailById(Long menuId) {
        SystemMenu systemMenu = baseMapper.selectById(menuId);
        return SystemMenuConvert.INSTANCE.toMenuResVO(systemMenu);
    }
}
