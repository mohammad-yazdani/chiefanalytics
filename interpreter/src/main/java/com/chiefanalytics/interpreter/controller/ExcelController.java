package com.chiefanalytics.interpreter.controller;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@RestController
public class ExcelController implements Controller {

    private static Logger log = LoggerFactory.getLogger(ExcelController.class);

    @Override
    public String getBase() {
        System.out.print(" Excel class implementing getBase()");
        return null;
    }

    @Override
    public String getHeader() {
        System.out.print(" Excel class implementing getHeader()");
        return null;
    }

    //    public boolean exploreKeyValue(XSSFSheet sheet, int row, int cell, int rowEndNum, int cellEndNum) {
//        if (row < 0 || row > rowEndNum || cell < 0 || cell > cellEndNum ||
//                sheet.getRow(row).getCell(cell).getStringCellValue() == "visited") {
//            return false;
//        }
//        int nextCell = cell + 1;
//        //System.out.printf("%s string \n", sheet.getRow(row).getCell(nextCell).getStringCellValue());
//        if (sheet.getRow(row).getCell(nextCell).getCellTypeEnum() == CellType.STRING ||
//                sheet.getRow(row).getCell(nextCell).getCellTypeEnum() == CellType.NUMERIC) {
//            sheet.getRow(row).getCell(cell).setCellValue("visited");
//            sheet.getRow(row).getCell(nextCell).setCellValue("visited");
//            return true;
//        }
//        return false;
//
//    }

    private boolean exploreTable(XSSFSheet sheet, int row, int cell, int rowEndNum, int cellEndNum) {
        if (row < 0 || row > rowEndNum || cell < 0 || cell > cellEndNum ||
                Objects.equals(sheet.getRow(row).getCell(cell).getStringCellValue(), "visited")) {
            return false;
        }

        // get the num of cols in the table
        int tableColLength = sheet.getRow(row).getLastCellNum() - cell;

        // get the num of rows in the table
        int tableRowLength = 0;
        for (int rowIndex = row; rowIndex <= rowEndNum; ++rowIndex) {
            try {
                if (sheet.getRow(rowIndex).getCell(cell).getCellTypeEnum() == CellType.STRING) {
                    ++tableRowLength;
                } else {
                    break;
                }
            } catch (Exception ignored) {
            }

        }

        // mark all contents of the table to be visited
        try {
            for (int i = 0; i < tableRowLength; ++i) {
                for (int j = 0; j < tableColLength; ++j) {
                    if (sheet.getRow(i + row).getCell(j + cell).getCellTypeEnum() == CellType.STRING) {
                        sheet.getRow(i + row).getCell(j + cell).setCellValue("visited");
                    }
                }

            }
        } catch (Exception e) {
            System.out.printf("ERROR IN MARKING VISITED\n");
        }
        return true;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

        try {

            log.info("File: " + file + " with name: " + name);

            String MY_FILE = "testFile.xlsx";
            File f = new File(MY_FILE);
            FileInputStream excelFile = new FileInputStream(f.getCanonicalPath());

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // int keyValueCount = 0;
            int tableCount = 0;
            int cellEnd = 0;
            int rowEnd = sheet.getLastRowNum();
            for (int i = 0; i <= rowEnd; ++i) {
                try {
                    cellEnd = cellEnd > sheet.getRow(i).getLastCellNum() ? cellEnd : sheet.getRow(i).getLastCellNum();
                } catch (Exception ignored) {}
            }

            //System.out.printf("%d row \n", rowEnd);
            //System.out.printf("%d cellEnd \n", cellEnd);

            for (int rowIndex = 0; rowIndex <= rowEnd; ++rowIndex) {
                for (int cellIndex = 0; cellIndex <= cellEnd; ++cellIndex) {
//                    System.out.printf("%d row \n", rowIndex);
//                    System.out.printf("%d cell \n", cellIndex);
                    try {
                        if (sheet.getRow(rowIndex).getCell(cellIndex).getCellTypeEnum() == CellType.STRING) {
//                            if (exploreKeyValue(sheet, rowIndex, cellIndex, rowEnd, cellEnd)) {
//                                ++keyValueCount;
//                            }
                            if (exploreTable(sheet, rowIndex, cellIndex, rowEnd, cellEnd)) {
                                ++tableCount;
                            }
                        }
                    } catch (Exception ignored) {}
                }
            }

            log.info("Getting file: "+ f.getCanonicalPath());
            System.out.printf("%d Table count \n", tableCount);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
