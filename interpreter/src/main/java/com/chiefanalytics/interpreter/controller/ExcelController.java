package com.chiefanalytics.interpreter.controller;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@RestController
public class ExcelController implements Controller {

    private static Logger log = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    RestTemplate restTemplate;

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

    private static String MY_FILE = "testFile.xlsx";

    public boolean explore(XSSFSheet sheet, int row, int cell, int rowEndNum, int cellEndNum) {
        if (row < 0 || row > rowEndNum || cell < 0 || cell > cellEndNum ||
                sheet.getRow(row).getCell(cell).getStringCellValue() == "visited") {
            return false;
        }
        int nextCell = cell + 1;
        System.out.printf("%s string \n", sheet.getRow(row).getCell(nextCell).getStringCellValue());
        if (sheet.getRow(row).getCell(nextCell).getCellTypeEnum() == CellType.STRING) {
            sheet.getRow(row).getCell(cell).setCellValue("visited");
            sheet.getRow(row).getCell(nextCell).setCellValue("visited");
            return true;
        }
        return false;

    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

        try {

            File f = new File(MY_FILE);
            FileInputStream excelFile = new FileInputStream(f.getCanonicalPath());

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            int count = 0;
            int rowEnd = sheet.getLastRowNum();
            int cellEnd = sheet.getRow(rowEnd).getLastCellNum();

            //System.out.printf("%d row \n", rowEnd);
            //System.out.printf("%d cell \n", cellEnd);

            for (int rowIndex = 0; rowIndex <= rowEnd; ++rowIndex) {
                for (int cellIndex = 0; cellIndex <= cellEnd; ++cellIndex) {
                    //System.out.printf("%d row \n", rowIndex);
                    //System.out.printf("%d cell \n", cellIndex);
                    try {
                        if (sheet.getRow(rowIndex).getCell(cellIndex).getCellTypeEnum() == CellType.STRING) {
                            if (explore(sheet, rowIndex, cellIndex, rowEnd, cellEnd)) {
                                ++count;
                            }
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }

            log.info("Getting file: "+ f.getCanonicalPath());
            System.out.printf("%d count \n", count);
            //Integer greeting = this.restTemplate.postForObject("http://localhost:8090/uploadFile", count, Integer);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
