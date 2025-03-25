ALTER TABLE system_dept
    ADD delete_flag      TINYINT(1) DEFAULT 0                 NOT NULL COMMENT '删除标志(0/false: 未删除, 1/true: 已删除)',
    ADD create_user      BIGINT                               NULL COMMENT '创建人',
    ADD create_user_name VARCHAR(32)                          NULL COMMENT '创建人名称',
#     ADD create_enterprise_id BIGINT                               NOT NULL COMMENT '创建人公司ID',
    ADD create_time      DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    ADD update_user      BIGINT                               NULL COMMENT '修改人',
    ADD update_time      DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间';