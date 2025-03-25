package com.temp.framework.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EasyExcel 自适应宽度
 *
 * @author Hollis
 * @since 2024-01-06 17:47
 */
public class CustomCellWriteHandler extends AbstractColumnWidthStyleStrategy {

    private Map<Integer, Map<Integer, Integer>> CACHE = new HashMap<>();

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head,
                                  Integer relativeRowIndex, Boolean isHead) {
        boolean needSetWidth = isHead || CollectionUtil.isNotEmpty(cellDataList);
        if (!needSetWidth) {
            return;
        }
        //宽度
        Integer columnWidth = this.dataLength(cellDataList, cell, isHead);
        if (columnWidth < 0) {
            return;
        }
        if (columnWidth > 150) {
            columnWidth = 150;
        }
        Map<Integer, Integer> maxColumnWidthMap = CACHE.computeIfAbsent(writeSheetHolder.getSheetNo(), k -> new HashMap<>());
        Integer maxColumnWidth = maxColumnWidthMap.get(cell.getColumnIndex());
        if (maxColumnWidth == null || columnWidth > maxColumnWidth) {
            columnWidth += 3;
            maxColumnWidthMap.put(cell.getColumnIndex(), columnWidth);
            writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
        }
    }

    private Integer dataLength(List<WriteCellData<?>> cellDataList, Cell cell, Boolean isHead) {
        if (isHead) {
            return cell.getStringCellValue().getBytes().length;
        } else {
            WriteCellData<?> cellData = cellDataList.get(0);
            CellDataTypeEnum type = cellData.getType();
            if (type == null) {
                return -1;
            } else {
                if (type == CellDataTypeEnum.STRING) {
                    return cellData.getStringValue().getBytes().length;
                } else if (type == CellDataTypeEnum.BOOLEAN) {
                    return cellData.getBooleanValue().toString().getBytes().length;
                } else if (type == CellDataTypeEnum.NUMBER) {
                    return cellData.getNumberValue().toString().getBytes().length;
                } else {
                    return -1;
                }
            }
        }
    }
}
