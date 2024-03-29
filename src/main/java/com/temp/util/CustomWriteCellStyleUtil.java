package com.temp.util;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import org.apache.poi.ss.usermodel.*;

/**
 * EasyExcel 自定义导出样式
 *
 * @author Hollis
 * @since 2024-01-06 17:47
 */
public class CustomWriteCellStyleUtil {

    /**
     * 标题样式
     *
     * @return {@link WriteCellStyle}
     */
    public static WriteCellStyle getHeadStyle() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();

        // 背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 字体
        WriteFont headWriteFont = new WriteFont();
        //设置字体名字
        headWriteFont.setFontName("黑体");
        //设置字体大小
        headWriteFont.setFontHeightInPoints((short) 10);
        //字体加粗
        headWriteFont.setBold(true);
        //字体颜色
        //headWriteFont.setColor(IndexedColors.WHITE.getIndex());
        //在样式用应用设置的字体;
        headWriteCellStyle.setWriteFont(headWriteFont);

        //设置底边框;
        headWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        headWriteCellStyle.setBottomBorderColor((short) 0);

        //设置左边框;
        headWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        headWriteCellStyle.setLeftBorderColor((short) 0);

        //设置右边框;
        headWriteCellStyle.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        headWriteCellStyle.setRightBorderColor((short) 0);

        //设置顶边框;
        headWriteCellStyle.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        headWriteCellStyle.setTopBorderColor((short) 0);

        //水平居中;
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //垂直居中;
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //禁用自动换行（标题不换行）;
        headWriteCellStyle.setWrapped(false);

        return headWriteCellStyle;
    }


    /**
     * 内容样式
     *
     * @return {@link WriteCellStyle}
     */
    public static WriteCellStyle getContentStyle() {
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        // 背景绿色
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 设置字体
        WriteFont contentWriteFont = new WriteFont();
        //设置字体大小
        contentWriteFont.setFontHeightInPoints((short) 10);
        //设置字体名字
        contentWriteFont.setFontName("宋体");
        //在样式用应用设置的字体;
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        //设置样式;
        //设置底边框;
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        contentWriteCellStyle.setBottomBorderColor((short) 0);

        //设置左边框;
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        contentWriteCellStyle.setLeftBorderColor((short) 0);

        //设置右边框;
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        contentWriteCellStyle.setRightBorderColor((short) 0);

        //设置顶边框;
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        contentWriteCellStyle.setTopBorderColor((short) 0);

        // 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置自动换行;
        contentWriteCellStyle.setWrapped(true);

        return contentWriteCellStyle;
    }
}
