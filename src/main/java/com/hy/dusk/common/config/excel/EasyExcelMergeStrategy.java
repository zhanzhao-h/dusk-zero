package com.hy.dusk.common.config.excel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: ZhaoZhan
 * @date: 2023-03-16 14:26
 */
public class EasyExcelMergeStrategy extends AbstractMergeStrategy {
    // 禁用无参
    private EasyExcelMergeStrategy() {
    }

    // 合并的列编号，从0开始，指定的index或自己按字段顺序数
    private final Set<Integer> mergeCellIndex = new HashSet<>();

    // 数据集大小，用于区别结束行位置
    private Integer maxRow = 0;

    //行合并的基准列：即按那列的值相等合并行,null走默认行合并策略
    private Integer mergeStandardCellIndex = null;

    public EasyExcelMergeStrategy(Integer maxRow, Integer mergeStandardCellIndex, int... mergeCellIndex) {
        Arrays.stream(mergeCellIndex).forEach(this.mergeCellIndex::add);
        this.maxRow = maxRow;
        this.mergeStandardCellIndex = mergeStandardCellIndex;
    }

    //记录上一次合并的信息
    private final Map<Integer, MergeRange> lastRow = new HashedMap<>();

    // 每行每列都会进入，绝对不要在这写循环
    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        int currentCellIndex = cell.getColumnIndex();
        // 判断该行是否需要合并
        if (mergeCellIndex.contains(currentCellIndex)) {
            String currentCellValue;
            if (mergeStandardCellIndex == null) {
                currentCellValue = new DataFormatter().formatCellValue(cell);
            } else {
                //此处固定比较第一列数据相同的进行行合并
                currentCellValue = new DataFormatter().formatCellValue(cell.getRow().getCell(mergeStandardCellIndex));
            }
            int currentRowIndex = cell.getRowIndex();
            if (!lastRow.containsKey(currentCellIndex)) {
                // 记录首行起始位置
                lastRow.put(currentCellIndex, new MergeRange(currentCellValue, currentRowIndex, currentRowIndex, currentCellIndex, currentCellIndex));
                return;
            }
            //有上行这列的值了，拿来对比.
            MergeRange mergeRange = lastRow.get(currentCellIndex);
            if (!(mergeRange.lastValue != null && mergeRange.lastValue.equals(currentCellValue))) {
                // 结束的位置触发下合并.
                // 同行同列不能合并，会抛异常
                if (mergeRange.startRow != mergeRange.endRow || mergeRange.startCell != mergeRange.endCell) {
                    sheet.addMergedRegionUnsafe(new CellRangeAddress(mergeRange.startRow, mergeRange.endRow, mergeRange.startCell, mergeRange.endCell));
                }
                // 更新当前列起始位置
                lastRow.put(currentCellIndex, new MergeRange(currentCellValue, currentRowIndex, currentRowIndex, currentCellIndex, currentCellIndex));
            }
            // 合并行 + 1
            mergeRange.endRow += 1;
            // 结束的位置触发下最后一次没完成的合并
            if (relativeRowIndex.equals(maxRow - 1)) {
                MergeRange lastMergeRange = lastRow.get(currentCellIndex);
                // 同行同列不能合并，会抛异常
                if (lastMergeRange.startRow != lastMergeRange.endRow || lastMergeRange.startCell != lastMergeRange.endCell) {
                    sheet.addMergedRegionUnsafe(new CellRangeAddress(
                            lastMergeRange.startRow,
                            lastMergeRange.endRow,
                            lastMergeRange.startCell,
                            lastMergeRange.endCell
                    ));
                }
            }
        }
    }
}

class MergeRange {
    public int startRow;
    public int endRow;
    public int startCell;
    public int endCell;
    public String lastValue;

    MergeRange(String lastValue, int startRow, int endRow, int startCell, int endCell) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCell = startCell;
        this.endCell = endCell;
        this.lastValue = lastValue;
    }
}
