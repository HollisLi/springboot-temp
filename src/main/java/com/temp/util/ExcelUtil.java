package com.temp.util;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.exception.ExcelCommonException;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.temp.handler.CustomCellWriteHandler;
import jakarta.servlet.ServletOutputStream;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.BaseXSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * EasyExcel 导入导出工具类
 *
 * @author Hollis
 * @see CustomCellWriteHandler
 * @see CustomWriteCellStyleUtil
 * @since 2024-01-06 17:47
 */
@Log4j2
public class ExcelUtil {

    /**
     * Read Excel With SheetName
     *
     * @param absoluteFilePath Excel文件绝对路径
     * @param dataClass        解析出来的实体类
     * @param sheetName        Excel 读取 sheet
     */
    public static <T> List<T> readExcel(String absoluteFilePath, Class<T> dataClass, String sheetName) {
        try {
            return EasyExcel
                    .read(absoluteFilePath, dataClass, null)
                    .sheet(sheetName)
                    .doReadSync();
        } catch (Exception ex) {
            log.error("读取Excel出错", ex);
            return null;
        }
    }


    /**
     * Read Excel With SheetName
     *
     * @param inputStream 文件流
     * @param dataClass   解析出来的实体类
     * @param sheetName   Excel 读取 sheet
     */
    public static <T> List<T> readExcel(InputStream inputStream, Class<T> dataClass, String sheetName) {
        try {
            return EasyExcel
                    .read(inputStream, dataClass, null)
                    .sheet(sheetName)
                    .doReadSync();
        } catch (Exception ex) {
            log.error("读取Excel出错", ex);
            return null;
        }
    }


    /**
     * Read Excel With SheetIndex
     *
     * @param absoluteFilePath Excel文件绝对路径
     * @param dataClass        解析出来的实体类
     * @param sheetIndex       sheet下标
     */
    public static <T> List<T> readExcel(String absoluteFilePath, Class<T> dataClass, Integer sheetIndex) {
        try {
            return EasyExcel
                    .read(absoluteFilePath, dataClass, null)
                    .sheet(sheetIndex)
                    .doReadSync();
        } catch (Exception ex) {
            log.error("读取Excel出错", ex);
            return null;
        }
    }


    // ----------------------- Write Excel Start -----------------------


    /**
     * Write Excel With SheetName
     *
     * @param absoluteFilePath 写入的Excel文件绝对路径
     * @param dataClass        解析的数据的对象
     * @param data             保存的数据来源
     * @param sheetName        保存的sheet表名
     */
    public static void writeExcel(String absoluteFilePath, Class<?> dataClass, List<?> data, String sheetName) {
        try {
            // 如果文件已存在，先删除
            FileUtil.del(absoluteFilePath);

            // 头的策略
            WriteCellStyle headWriteCellStyle = CustomWriteCellStyleUtil.getHeadStyle();
            // 内容的策略
            WriteCellStyle contentWriteCellStyle = CustomWriteCellStyleUtil.getContentStyle();
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            // 写入excel
            EasyExcel
                    .write(absoluteFilePath, dataClass)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet(sheetName)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .doWrite(data);
        } catch (Exception ex) {
            log.error("写入Excel出错", ex);
        }
    }


    /**
     * Write Excel With SheetName
     *
     * @param outputStream 文件输出流
     * @param dataClass    解析的数据的对象
     * @param data         保存的数据来源
     * @param sheetName    保存的sheet表名
     */
    public static void writeExcel(ServletOutputStream outputStream, Class<?> dataClass, List<?> data, String sheetName) {
        try {
            // 头的策略
            WriteCellStyle headWriteCellStyle = CustomWriteCellStyleUtil.getHeadStyle();
            // 内容的策略
            WriteCellStyle contentWriteCellStyle = CustomWriteCellStyleUtil.getContentStyle();
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            // 写入excel
            EasyExcel
                    .write(outputStream, dataClass)
                    .sheet(sheetName)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .doWrite(data);
        } catch (Exception ex) {
            log.error("导出Excel出错", ex);
        }
    }


    /**
     * Write Excel With SheetName
     *
     * @param outputStream            文件输出流
     * @param dataClass               解析的数据的对象
     * @param data                    保存的数据来源
     * @param sheetName               保存的sheet表名
     * @param excludeColumnFieldNames 导出时忽略的字段名
     */
    public static void writeExcel(ServletOutputStream outputStream,
                                  Class<?> dataClass,
                                  List<?> data,
                                  String sheetName,
                                  List<String> excludeColumnFieldNames) {
        try {
            // 头的策略
            WriteCellStyle headWriteCellStyle = CustomWriteCellStyleUtil.getHeadStyle();
            // 内容的策略
            WriteCellStyle contentWriteCellStyle = CustomWriteCellStyleUtil.getContentStyle();
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            // 写入excel
            EasyExcel
                    .write(outputStream, dataClass)
                    .sheet(sheetName)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .excludeColumnFieldNames(excludeColumnFieldNames)
                    .doWrite(data);
        } catch (Exception ex) {
            log.error("导出Excel出错", ex);
        }
    }


    /**
     * 读取 Excel 指定 Sheet 中指定行列坐标的值
     *
     * @param fileName  解析文件名
     * @param sheetName sheet名
     * @param line      行号
     * @param column    列号
     * @return java.lang.String Excel指定Sheet的指定坐标值
     * @author Hollis
     */
    public static String getData(String fileName, String sheetName, int line, int column) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = getWorkbook(fileInputStream, fileName);
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                Row row = sheet.getRow(line);
                if (row != null) {
                    Cell cell = row.getCell(column);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        return cell.getStringCellValue();
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            log.error("读取excel出错, errorMessage = {}", ex.getMessage());
            return null;
        }
    }


    /**
     * 读取 Excel 指定 Sheet 中指定行列坐标的值
     *
     * @param fileName   解析文件名
     * @param sheetIndex sheet下标
     * @param line       行号
     * @param column     列号
     * @return java.lang.String Excel指定Sheet的指定坐标值
     * @author Hollis
     */
    public static String getData(String fileName, int sheetIndex, int line, int column) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = getWorkbook(fileInputStream, fileName);
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                log.error("读取excel出错, sheet is null, fileName: {}, sheetIndex: {}", fileName, sheetIndex);
                return null;
            }

            Row row = sheet.getRow(line);
            if (null == row) {
                log.error("读取excel出错, row is null, fileName: {}, sheetIndex: {}, row: {}",
                        fileName, sheetIndex, line);
                return null;
            }

            Cell cell = row.getCell(column);
            if (cell == null) {
                log.error("读取excel出错, column is null, fileName: {}, sheetIndex: {}, row: {}, column: {}",
                        fileName, sheetIndex, line, column);
                return null;
            }

            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return String.valueOf(cell.getNumericCellValue());
                }
                case STRING -> {
                    return cell.getStringCellValue();
                }
                case FORMULA -> {
                    String cellFormula = cell.getCellFormula();
                    if (StringUtils.isBlank(cellFormula)) {
                        return StringUtils.EMPTY;
                    }
                    return getFormulaEvaluator(workbook, fileName)
                            .evaluate(cell)
                            .formatAsString();
                }
                case BLANK -> {
                    return StringUtils.EMPTY;
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
                }
                case ERROR -> {
                    return ErrorEval.getText(cell.getErrorCellValue());
                }
                default -> {
                    log.error("读取excel出错, 未知 Cell 类型: {}", cell.getCellType());
                    return null;
                }
            }

        } catch (Exception ex) {
            log.error("读取excel出错, errorMessage = {}", ex.getMessage());
            return null;
        }
    }

    public static Workbook getWorkbook(FileInputStream fileInputStream, String fileName) throws IOException {
        if (fileName.endsWith(ExcelTypeEnum.XLSX.getValue())) {
            return new XSSFWorkbook(fileInputStream);
        } else if (fileName.endsWith(ExcelTypeEnum.XLS.getValue())) {
            return new HSSFWorkbook(fileInputStream);
        }
        throw new ExcelCommonException("ExcelUtil#getWorkbook error, 获取 Workbook 对象异常!");
    }

    public static BaseXSSFFormulaEvaluator getFormulaEvaluator(Workbook workbook, String fileName) throws IOException {
        if (fileName.endsWith(ExcelTypeEnum.XLSX.getValue())) {
            return new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
        } else if (fileName.endsWith(ExcelTypeEnum.XLS.getValue())) {
            return new SXSSFFormulaEvaluator((SXSSFWorkbook) workbook);
        }
        throw new ExcelCommonException("ExcelUtil#getFormulaEvaluator error, 获取公式计算对象异常!");
    }
}
