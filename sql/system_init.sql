create table system_dept
(
    id               bigint comment '部门id'
        primary key,
    name             varchar(30)                        not null comment '部门名称',
    enterprise_id    bigint                             not null comment '企业id',
    parent_id        bigint   default 0                 not null comment '父部门id',
    sort             int      default 0                 not null comment '显示顺序',
    leader_user_id   bigint                             null comment '负责人',
    phone            varchar(11)                        null comment '联系电话',
    email            varchar(50)                        null comment '邮箱',
    status           tinyint  default 0                 not null comment '部门状态（0正常 1停用）',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      bigint                             null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      varchar(32)                        null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '部门表';

create table system_dict_type
(
    id               bigint comment '字典主键'
        primary key,
    name             varchar(100)                       not null comment '字典名称',
    type             varchar(100)                       not null comment '字典类型',
    status           tinyint  default 0                 not null comment '状态（0正常 1停用）',
    remark           varchar(500)                       null comment '备注',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      bigint                             null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      bigint                             null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint dict_type
        unique (type)
)
    comment '字典类型表';

create table system_dict_data
(
    id               bigint comment '字典键值主键'
        primary key,
    label            varchar(100)                       not null comment '字典标签',
    value            varchar(100)                       not null comment '字典键值',
    dict_type        varchar(100)                       not null comment '字典类型',
    sort             int      default 0                 not null comment '字典排序',
    color_type       varchar(100)                       null comment '颜色类型[@see: https://arco.design/vue/component/tag,带边框的标签]',
    css_class        varchar(100)                       null comment 'css样式',
    remark           varchar(500)                       null comment '备注',
    status           tinyint  default 0                 not null comment '状态（0正常 1停用）',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      bigint                             null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      bigint                             null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '字典数据表';

create table system_enterprise
(
    id                         bigint comment '企业ID'
        primary key,
    enterprise_name            varchar(125)                       not null comment '企业名称',
    unified_social_credit_code varchar(125)                       null comment '统一社会信用代码',
    admin_user                 bigint                             null comment '管理员用户ID',
    status                     tinyint  default 0                 not null comment '企业状态（0正常 1停用）',
    delete_flag                bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user                varchar(32)                        null comment '创建人',
    create_user_name           varchar(32)                        null comment '创建人名称',
    create_time                datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user                varchar(32)                        null comment '修改人',
    update_time                datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '企业信息表';

create index idx_enterprise_name
    on system_enterprise (enterprise_name);

create table system_login_log
(
    id               bigint comment '访问ID'
        primary key,
    log_type         bigint                             not null comment '日志类型, 账号登录: 100, 主动登出: 200, 强制退出: 202',
    user_id          bigint   default 0                 not null comment '用户编号',
    username         varchar(50)                        not null comment '用户账号',
    result           tinyint                            not null comment '登陆结果, 成功: 0, 账号或密码不正确: 10, 用户被禁用: 20',
    user_ip          varchar(50)                        not null comment '用户 IP',
    user_agent       varchar(512)                       not null comment '浏览器 UA',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      bigint                             null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      bigint                             null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '系统访问记录';

create index idx_username
    on system_login_log (username);

create table system_menu
(
    id               bigint comment '菜单ID'
        primary key,
    name             varchar(50)                            not null comment '菜单名称',
    permission       varchar(100)                           not null comment '权限标识',
    type             tinyint                                not null comment '菜单类型 1:目录 2:菜单 3:按钮',
    sort             int          default 0                 not null comment '显示顺序',
    parent_id        bigint       default 0                 not null comment '父菜单ID',
    path             varchar(200)                           null comment '路由地址',
    icon             varchar(100) default '#'               null comment '菜单图标',
    component        varchar(255)                           null comment '组件路径',
    component_name   varchar(255)                           null comment '组件名',
    status           tinyint      default 0                 not null comment '菜单状态',
    visible          bit          default b'1'              not null comment '是否可见',
    keep_alive       bit          default b'1'              not null comment '是否缓存',
    always_show      bit          default b'1'              not null comment '是否总是显示',
    delete_flag      bit          default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      varchar(32)                            null comment '创建人',
    create_user_name varchar(32)                            null comment '创建人名称',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      varchar(32)                            null comment '修改人',
    update_time      datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '菜单权限表';

create table system_post
(
    id               bigint comment '岗位ID'
        primary key,
    name             varchar(50)                        not null comment '岗位名称',
    sort             int                                not null comment '显示顺序',
    status           tinyint  default 0                 not null comment '状态（0正常 1停用）',
    remark           varchar(500)                       null comment '备注',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      bigint                             null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      bigint                             null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '岗位信息表';

create table system_role
(
    id                  bigint comment '角色ID'
        primary key,
    name                varchar(30)                        not null comment '角色名称',
    code                varchar(100)                       not null comment '角色权限字符串',
    sort                int                                not null comment '显示顺序',
    data_scope          tinyint  default 1                 not null comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限 5: 仅本人数据权限）',
    data_scope_dept_ids varchar(500)                       not null comment '数据范围(指定部门数组)',
    status              tinyint  default 0                 not null comment '角色状态（0正常 1停用）',
    remark              varchar(500)                       null comment '备注',
    delete_flag         bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user         varchar(32)                        null comment '创建人',
    create_user_name    varchar(32)                        null comment '创建人名称',
    create_time         datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user         varchar(32)                        null comment '修改人',
    update_time         datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '角色信息表';

create table system_role_menu
(
    id               bigint comment '自增编号'
        primary key,
    role_id          bigint                             not null comment '角色ID',
    menu_id          bigint                             not null comment '菜单ID',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      bigint                             null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      bigint                             null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '角色和菜单关联表';

create table system_user_post
(
    id               bigint comment 'id'
        primary key,
    user_id          bigint   default 0                 not null comment '用户ID',
    post_id          bigint   default 0                 not null comment '岗位ID',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      bigint                             null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      bigint                             null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '用户岗位表';

create table system_user_role
(
    id               bigint comment '自增编号'
        primary key,
    user_id          bigint                             not null comment '用户ID',
    role_id          bigint                             not null comment '角色ID',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      bigint                             null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      bigint                             null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '用户和角色关联表';

create table system_users
(
    id               bigint comment '用户ID'
        primary key,
    username         varchar(30)                        not null comment '登录账号',
    password         varchar(100)                       not null comment '登录密码',
    nickname         varchar(30)                        not null comment '用户昵称',
    avatar           text                               null comment '头像地址',
    email            varchar(50)                        null comment '用户邮箱',
    mobile           varchar(11)                        null comment '手机号码',
    sex              tinyint                            null comment '用户性别, 0:女 1:男',
    enterprise_id    bigint                             not null comment '企业ID',
    dept_id          bigint                             not null comment '部门ID',
    login_ip         varchar(50)                        null comment '最后登录IP',
    login_date       datetime                           null comment '最后登录时间',
    status           bit      default b'0'              not null comment '帐号状态（0正常 1停用）',
    remark           varchar(500)                       null comment '备注',
    delete_flag      bit      default b'0'              not null comment '删除标志(0/false-未删除、1/true-已删除)',
    create_user      varchar(32)                        null comment '创建人',
    create_user_name varchar(32)                        null comment '创建人名称',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user      varchar(32)                        null comment '修改人',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '用户信息表';