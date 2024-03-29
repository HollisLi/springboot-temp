package com.temp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * MybatisPlus 新增/修改时自动填充公共字段
 *
 * @author Hollis
 * @since 2024-01-06 15:55
 */
@Log4j2
@Component
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增时填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (metaObject.hasSetter("createTime")) {
                this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
            }
            if (metaObject.hasSetter("updateTime")) {
                this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
            }
        } catch (Exception e) {
            log.warn("MyMetaObjectHandler#insertFill hasError: {}", e.getMessage(), e);
        }
    }

    /**
     * 修改时填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (metaObject.hasSetter("updateTime")) {
                this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
            }
        } catch (Exception e) {
            log.warn("MyMetaObjectHandler#updateFill hasError: {}", e.getMessage(), e);
        }
    }
}