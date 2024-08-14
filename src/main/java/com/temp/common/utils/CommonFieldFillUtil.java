package com.temp.common.utils;

import com.greatmicro.foundation.commons.web.http.HeaderHelper;
import com.greatmicro.foundation.id.worker.client.IdWorkerProxy;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 向对象填充新增/修改时的公共字段值
 *
 * @author Hollis
 * @since 2024/08/02 下午4:27
 */
@Log4j2
public class CommonFieldFillUtil {

    private final static String ID = "id";
    private final static String TENANT_ID = "tenantId";
    private final static String DELETE_FLAG = "deleteFlag";
    private final static String UPDATE_USER = "updateUser";
    private final static String UPDATE_TIME = "updateTime";
    private final static String CREATE_USER = "createUser";
    private final static String CREATE_TIME = "createTime";
    private final static String CREATE_ENTERPRISE_ID = "createEnterpriseId";

    /**
     * <pre>
     * 新增时的公共字段值填充
     * 注: 当下述字段存在时, 无论字段值是否为 null, 都会赋值
     * 填充字段:
     *   id
     *   tenantId
     *   deleteFlag
     *   updateUser
     *   updateTime
     *   createUser
     *   createEnterpriseId
     *   createTime
     * </pre>
     *
     * @param obj 对象
     */
    public static void insertFill(Object obj) {
        fillFields(obj, true);
    }

    /**
     * <pre>
     * 新增时的公共字段值填充
     * 注: 当下述字段存在 且 字段值为 null 时, 才会赋值
     * 填充字段:
     *   id
     *   tenantId
     *   deleteFlag
     *   updateUser
     *   updateTime
     *   createUser
     *   createEnterpriseId
     *   createTime
     * </pre>
     *
     * @param obj 对象
     */
    public static void insertSelectiveFill(Object obj) {
        fillFields(obj, false);
    }

    /**
     * <pre>
     * 修改时的公共字段值填充
     * 注: 当下述字段存在时, 无论字段值是否为 null, 都会赋值
     * 填充字段:
     *   updateUser, 当前登录用户Id
     *   updateTime
     * </pre>
     *
     * @param obj 对象
     */
    public static void updateFill(Object obj) {
        fillField(obj, UPDATE_USER, HeaderHelper.getCustomerId(), true);
        fillField(obj, UPDATE_TIME, new Date(), true);
    }

    /**
     * <pre>
     * 修改时的公共字段值填充
     * 注: 当下述字段存在 且 字段值为 null 时, 才会赋值
     * 填充字段:
     *   updateUser, 当前登录用户Id
     *   updateTime
     * </pre>
     *
     * @param obj 对象
     */
    public static void updateSelectiveFill(Object obj) {
        fillField(obj, UPDATE_USER, HeaderHelper.getCustomerId(), false);
        fillField(obj, UPDATE_TIME, new Date(), false);
    }

    private static void fillFields(Object obj, boolean forceFill) {
        Date now = new Date();
        fillField(obj, ID, IdWorkerProxy.generateId(), forceFill);
        fillField(obj, TENANT_ID, HeaderHelper.getTenantId(), forceFill);
        fillField(obj, DELETE_FLAG, Boolean.FALSE, forceFill);
        fillField(obj, UPDATE_USER, HeaderHelper.getCustomerId(), forceFill);
        fillField(obj, UPDATE_TIME, now, forceFill);
        fillField(obj, CREATE_USER, HeaderHelper.getCustomerId(), forceFill);
        fillField(obj, CREATE_ENTERPRISE_ID, HeaderHelper.getEnterpriseId(), forceFill);
        fillField(obj, CREATE_TIME, now, forceFill);
    }

    private static void fillField(Object obj, String fieldName, Object fieldVal, boolean forceFill) {
        try {
            if (hasField(obj, fieldName) && (forceFill || getFieldValue(obj, fieldName) == null)) {
                setFieldValue(obj, fieldName, fieldVal);
            }
        } catch (Exception e) {
            log.error("CommonFieldFillUtil#fillField Error, fieldName: {}, fieldVal: {}, errorMessage: {}", fieldName, fieldVal, e.getMessage(), e);
        }
    }

    private static boolean hasField(Object obj, String fieldName) {
        try {
            obj.getClass().getDeclaredField(fieldName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Object getFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    private static void setFieldValue(Object obj, String fieldName, Object fieldVal) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, fieldVal);
    }
}
