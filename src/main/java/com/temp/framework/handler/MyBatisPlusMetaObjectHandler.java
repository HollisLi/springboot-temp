package com.temp.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.temp.biz.entity.BaseEntity;
import com.temp.framework.security.TokenHolder;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Objects;


/**
 * MybatisPlus 新增/修改时自动填充公共字段
 *
 * @author Hollis
 * @since 2024-01-06 15:55
 */
@Log4j2
@Component
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    private static final String MOCK_USER_NAME = "Mock User";
    private static final String ENVIRONMENT_LOCAL = "local";

    @Value("${spring.profiles.active}")
    private String activeEnvironment;

    /**
     * 新增时填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (ObjectUtils.isNull(metaObject)) {
            return;
        }
        if (!(metaObject.getOriginalObject() instanceof BaseEntity)) {
            return;
        }

        ZonedDateTime now = ZonedDateTime.now();
        BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
        if (Objects.isNull(baseEntity.getCreateTime())) {
            this.strictInsertFill(metaObject, "createTime", ZonedDateTime.class, now);
        }
        if (Objects.isNull(baseEntity.getUpdateTime())) {
            this.strictInsertFill(metaObject, "updateTime", ZonedDateTime.class, now);
        }

        String nickName = TokenHolder.getNickName();
        // 本地环境下，模拟用户
        if (StringUtils.isBlank(nickName) && ENVIRONMENT_LOCAL.equals(activeEnvironment)) {
            nickName = MOCK_USER_NAME;
        }
        if (StringUtils.isBlank(nickName)) {
            return;
        }
        if (null == baseEntity.getCreateUser()) {
            this.strictInsertFill(metaObject, "createUser", String.class, nickName);
        }
        if (null == baseEntity.getUpdateUser()) {
            this.strictInsertFill(metaObject, "updateUser", String.class, nickName);
        }
    }

    /**
     * 修改时填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (ObjectUtils.isNull(metaObject)) {
            return;
        }
        if (!(metaObject.getOriginalObject() instanceof BaseEntity)) {
            return;
        }

        BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
        if (Objects.isNull(baseEntity.getUpdateTime())) {
            this.strictInsertFill(metaObject, "updateTime", ZonedDateTime.class, ZonedDateTime.now());
        }

        String nickName = TokenHolder.getNickName();
        // 本地环境下，模拟用户
        if (StringUtils.isBlank(nickName) && ENVIRONMENT_LOCAL.equals(activeEnvironment)) {
            nickName = "Mock User";
        }
        if (StringUtils.isBlank(nickName)) {
            return;
        }
        if (null == baseEntity.getUpdateUser()) {
            this.strictInsertFill(metaObject, "updateUser", String.class, nickName);
        }
    }
}