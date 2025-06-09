package com.example.playground.stable.excel;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelParser {
    private static final String EXCEL_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String EXCEL_EXTENSION_XLSX = "xlsx";
    private static final String EXCEL_EXTENSION_XLS = "xls";

    public static boolean isExcelFile(MultipartFile file) {
        if (Objects.isNull(file.getOriginalFilename()) || file.getOriginalFilename().isEmpty()) {
            return false;
        }
        if (Objects.nonNull(file.getContentType()) && file.getContentType().contains(EXCEL_MIME_TYPE)) {
            return true;
        }
        return file.getOriginalFilename().endsWith(EXCEL_EXTENSION_XLSX) ||
                file.getOriginalFilename().endsWith(EXCEL_EXTENSION_XLS);
    }

    public static Workbook parseWorkBook(MultipartFile file) {
        try {
            return new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Sheet getSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("엑셀 시트가 존재하지 않습니다: " + sheetName);
        }
        return sheet;
    }

    public static Iterator<Row> getSheetIterator(Workbook workbook, String sheetName) {
        Sheet sheet = getSheet(workbook, sheetName);
        return sheet.iterator();
    }

    public static List<Row> getDataRows(Sheet sheet, int skipRows) {
        List<Row> dataRows = new ArrayList<>();
        int index = 0;
        for (Row row : sheet) {
            index++;
            if (index <= skipRows) continue;
            dataRows.add(row);
        }
        return dataRows;
    }

    public static boolean isRowEmpty(Row row, List<Integer> requiredColumnIndexes) {
        if (row == null) return true;
        int index = 0;
        for (Cell cell : row) {
            if (!requiredColumnIndexes.contains(index)) {
                index++;
                continue;
            }
            if (getStringCellValue(cell).isEmpty()) {
                return true;
            }
            index++;
        }
        return false;
    }

    public static String getStringCellValue(Cell cell) {
        if (Objects.isNull(cell)) return "";
        if (cell.getCellType() == CellType.BLANK) return "";
        return cell.toString().trim();
    }
}
