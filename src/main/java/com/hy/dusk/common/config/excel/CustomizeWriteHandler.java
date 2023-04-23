package com.hy.dusk.common.config.excel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * @author: ZhaoZhan
 * @date: 2023-04-23 19:38
 */
public class CustomizeWriteHandler implements CellWriteHandler {
    @Override
    public void beforeCellCreate(
            WriteSheetHolder writeSheetHolder,
            WriteTableHolder writeTableHolder,
            Row row,
            Head head,
            Integer columnIndex,
            Integer relativeRowIndex,
            Boolean isHead
    ) {
        CellWriteHandler.super.beforeCellCreate(writeSheetHolder, writeTableHolder, row, head, columnIndex, relativeRowIndex, isHead);
    }

    @Override
    public void afterCellCreate(
            WriteSheetHolder writeSheetHolder,
            WriteTableHolder writeTableHolder,
            Cell cell,
            Head head,
            Integer relativeRowIndex,
            Boolean isHead
    ) {
        CellWriteHandler.super.afterCellCreate(writeSheetHolder, writeTableHolder, cell, head, relativeRowIndex, isHead);
    }

    @Override
    public void afterCellDataConverted(
            WriteSheetHolder writeSheetHolder,
            WriteTableHolder writeTableHolder,
            WriteCellData<?> cellData,
            Cell cell,
            Head head,
            Integer relativeRowIndex,
            Boolean isHead
    ) {
        CellWriteHandler.super.afterCellDataConverted(writeSheetHolder, writeTableHolder, cellData, cell, head, relativeRowIndex, isHead);
    }

    @Override
    public void afterCellDispose(
            WriteSheetHolder writeSheetHolder,
            WriteTableHolder writeTableHolder,
            List<WriteCellData<?>> cellDataList,
            Cell cell,
            Head head,
            Integer relativeRowIndex,
            Boolean isHead
    ) {
        if (!isHead && cell.getCellType() == CellType.BLANK) {
            cell.setCellValue("-");
        }
    }
}
