package com.temp.biz.controller.system;

import com.temp.biz.domain.vo.system.menu.MenuListReqVO;
import com.temp.biz.domain.vo.system.menu.MenuResVO;
import com.temp.biz.domain.vo.system.menu.MenuSaveReqVO;
import com.temp.biz.domain.vo.system.menu.MenuSimpleRespVO;
import com.temp.biz.service.system.SystemMenuService;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 管理后台 - 菜单
 *
 * @author Hollis
 * @since 2024/06/13 下午2:57
 */
@Validated
@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
public class MenuController {

    private final SystemMenuService systemMenuService;

    /**
     * 创建菜单
     *
     * @param request 菜单信息
     * @return {@link Long } 菜单Id
     */
    @PostMapping("/create")
    public Long create(@RequestBody @Valid MenuSaveReqVO request) {
        return systemMenuService.insert(request);
    }

    /**
     * 修改菜单
     *
     * @param request 菜单信息
     */
    @PutMapping("/update")
    public void update(@RequestBody @Valid MenuSaveReqVO request) {
        if (request.getId() == null) {
            throw new BusinessException("菜单Id不能为空");
        }
        systemMenuService.update(request);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单Id
     */
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        systemMenuService.delete(id);
    }

    /**
     * 获取菜单列表
     *
     * @param request 筛选条件
     * @return {@link List }<{@link MenuResVO }> 菜单列表信息
     */
    @PostMapping("/list")
    public List<MenuResVO> list(@RequestBody MenuListReqVO request) {
        return systemMenuService.list(request);
    }

    /**
     * 获取菜单精简信息列表
     * <p>
     * 只包含被开启的菜单，用于【角色分配菜单】功能的选项。
     *
     * @return {@link List }<{@link MenuSimpleRespVO }> 精简菜单列表信息
     */
    @GetMapping("/simple-list")
    public List<MenuSimpleRespVO> simpleList() {
        return systemMenuService.simpleList();
    }

    /**
     * 获取菜单详情
     *
     * @param id 菜单Id
     */
    @GetMapping("/get")
    public MenuResVO getDetailById(@RequestParam("id") Long id) {
        return systemMenuService.getDetailById(id);
    }
}
