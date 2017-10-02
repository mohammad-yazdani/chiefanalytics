package com.chiefanalytics.interpreter.controller;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import java.io.IOException;

@RestController
public class ExcelController implements Controller {

    private static String MY_FILE = "testFile.xlsx";

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

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile File) {
        try {
            File f = new File(MY_FILE);
            FileInputStream excelFile = new FileInputStream(f);
            System.out.println("Getting file: "+ f.getCanonicalPath());
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            // prints out lines from excel file
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
