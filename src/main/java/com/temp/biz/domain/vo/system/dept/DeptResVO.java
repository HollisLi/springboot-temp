package com.temp.biz.domain.vo.system.dept;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * 部门信息 Response VO
 *
 * @author Hollis
 * @since 2024-06-03 18:24
 */
@Data
public class DeptResVO implements Serializable {

    private static final long serialVersionUID = 1274914341158991316L;

    /**
     * 部门ID
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 企业id
     */
    private Long enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 父部门 ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 负责人的用户Id
     */
    private Long leaderUserId;

    /**
     * 负责人的用户名
     */
    private String leaderUserName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createTime;

}
