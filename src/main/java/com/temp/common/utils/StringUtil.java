package com.temp.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Hollis
 * @since 2023/07/11 9:47
 */
public class StringUtil {

    /**
     * 移除字符串的所有()和中间的内容 <br/>
     * e.g: <br/>
     * 入参 str = 测试文件(1)(2)(3).txt
     * 返回 测试文件.txt
     *
     * @param str 需要删除括号和括号中间内容的字符串
     * @return java.lang.String
     * @author Hollis
     */
    public static String removeBrackets(String str) {
        if (StringUtils.isBlank(str)) {
            return StringUtils.EMPTY;
        }
        return str.replaceAll("(\\(|\\（)\\d+(\\）|\\))", "");
    }

    /**
     * 移除字符串的最后一个()和中间的内容 <br/>
     * e.g: <br/>
     * 入参 str = 测试文件(1)(2)(3).txt
     * 返回 测试文件(1)(2).txt
     *
     * @param fileName 需要删除括号和括号中间内容的字符串
     * @return java.lang.String
     * @author Hollis
     */
    public static String removeLastBracketSet(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return StringUtils.EMPTY;
        }

        int lastIndexOfChineseLeftBracket = fileName.lastIndexOf("（");
        int lastIndexOfEnglishLeftBracket = fileName.lastIndexOf("(");
        int lastLeftBracket = Math.max(lastIndexOfEnglishLeftBracket, lastIndexOfChineseLeftBracket);
        if (lastIndexOfChineseLeftBracket != -1 || lastIndexOfEnglishLeftBracket != -1) {
            String ext = StringUtils.substringAfter(fileName, ".");
            String firstPart = StringUtils.substring(fileName, 0, lastLeftBracket);
            fileName = firstPart + "." + ext;
        }

        return fileName;
    }

}
