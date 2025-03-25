package com.temp.common.constants;

/**
 * 错误码常量类
 *
 * @author Hollis
 * @since 2024-07-12 10:56
 */
public interface ErrorMsgConstant {

    // 企业模块
    String ENTERPRISE_HAS_USER = "企业下存在员工，无法删除";
    String ENTERPRISE_HAS_DEPT = "企业下存在部门，无法删除";
    String ENTERPRISE_HAS_ROLE = "企业下存在角色，无法删除";
    String ENTERPRISE_NOT_FOUND = "当前企业不存在";
    String ENTERPRISE_NAME_DUPLICATE = "企业名称已存在";

    // 部门模块
    String DEPT_NOT_FOUND = "当前部门不存在";
    String DEPT_NOT_ENABLE = "部门({})不处于开启状态，不允许选择";
    String DEPT_PARENT_ERROR = "不能设置自己为父部门";
    String DEPT_NAME_DUPLICATE = "部门名称已存在";
    String DEPT_EXITS_USER = "部门中存在员工，无法删除";
    String DEPT_EXITS_CHILDREN = "存在子部门，无法删除";
    String DEPT_PARENT_IS_CHILD = "不能设置自己的子部门为父部门";
    String DEPT_PARENT_NOT_EXITS = "父级部门不存在";
    String DEPT_PARENT_ENTERPRISE_ERROR = "父级部门和当前部门不归属同一个企业";

    // 岗位模块
    String POST_NOT_FOUND = "当前岗位不存在";
    String POST_NAME_DUPLICATE = "岗位名称已存在";

    // 角色模块
    String ROLE_NOT_EXISTS = "角色不存在";
    String ROLE_NAME_DUPLICATE = "已经存在名为【{}】的角色";
    String ROLE_CODE_DUPLICATE = "已经存在编码为【{}】的角色";
    String ROLE_ADMIN_CODE_ERROR = "编码【{}】不能使用";
    String ROLE_SUPER_ADMIN_CANT_DELETE = "编码【{}】角色不能删除";

    // 用户模块
    String USER_NOT_FOUND = "当前账号不存在";
    String USER_NAME_DUPLICATE = "登录账号已存在";

    // 菜单模块
    String MENU_NOT_EXISTS = "菜单不存在";
    String MENU_PARENT_ERROR = "不能设置自己为父菜单";
    String MENU_NAME_DUPLICATE = "当前父节点下已经存在该名字的菜单";
    String MENU_PARENT_NOT_EXISTS = "父菜单不存在";
    String MENU_PARENT_NOT_DIR_OR_MENU = "父菜单的类型必须是目录或者菜单";
    String MENU_EXISTS_CHILDREN = "存在子菜单，无法删除";
}
