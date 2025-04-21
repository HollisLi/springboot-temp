package com.temp.common.utils;

import cn.hutool.http.HttpUtil;
import lombok.extern.log4j.Log4j2;

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
public class WeComWebHookNotifyUtil {

    private static final String WEB_HOOK_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=ae35d19a-e41d-4522-bd74-85f050aa3b67";


    /**
     * 发送企业微信 WebHook 通知
     *
     * @param msg    消息内容
     * @param mobile @用户手机号
     * @return {@link String }
     */
    public static String sendWeChatHookNotify(String msg, String mobile) {
        return sendWeChatHookNotify(msg, Collections.singletonList(mobile));
    }

    /**
     * 发送企业微信 WebHook 通知
     *
     * @param msg        消息内容
     * @param mobileList @用户手机号列表
     * @return {@link String }
     */
    public static String sendWeChatHookNotify(String msg, List<String> mobileList) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("msgtype", "text");
        Map<String, Object> textMap = new HashMap<>();
        textMap.put("content", msg);
        textMap.put("mentioned_mobile_list", mobileList);
        paramMap.put("text", textMap);
        try {
            String result = HttpUtil.post(WEB_HOOK_URL, paramMap);
            log.info("[企业微信] 群通知 WebHook 调用成功, callPath:{}, request:{}, response:{}", WEB_HOOK_URL, paramMap, result);
            return result;
        } catch (Exception e) {
            log.error("[企业微信] 群通知 WebHook 调用失败, callPath:{}, request:{}, errorMessage:{}", WEB_HOOK_URL, paramMap, e.getMessage(), e);
            return null;
        }
    }

}
