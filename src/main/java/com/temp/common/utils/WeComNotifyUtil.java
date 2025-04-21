package com.temp.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业微信 WebHook 通知工具类
 *
 * @author Hollis
 * @since 2025/04/21 14:48
 */
@Log4j2
@Component
public class WeComNotifyUtil {

    @Value("${wecom.web-hook-url}")
    private String webHookUrl;


    /**
     * 发送企业微信 WebHook 通知
     *
     * @param msg    消息内容
     * @param mobile @用户手机号
     * @return {@link String }
     */
    public String send(String mobile, String msg) {
        return send(Collections.singletonList(mobile), msg);
    }

    /**
     * 发送企业微信 WebHook 通知
     *
     * @param msg        消息内容
     * @param mobileList @用户手机号列表
     * @return {@link String }
     */
    public String send(List<String> mobileList, String msg) {
        Map<String, Object> textMap = new HashMap<>();
        textMap.put("content", msg);
        textMap.put("mentioned_mobile_list", mobileList);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("msgtype", "text");
        paramMap.put("text", textMap);
        try {
            String result = HttpUtil.post(webHookUrl, JSONUtil.toJsonStr(paramMap));
            log.info("[企业微信] 群通知 WebHook 调用成功, callPath:{}, request:{}, response:{}", webHookUrl, paramMap, result);
            return result;
        } catch (Exception e) {
            log.error("[企业微信] 群通知 WebHook 调用失败, callPath:{}, request:{}, errorMessage:{}", webHookUrl, paramMap, e.getMessage(), e);
            return null;
        }
    }

}
