package com.chiefanalytics.interpreter.controller;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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


    public boolean exploreTable(XSSFSheet sheet, int row, int cell, int rowEndNum, int cellEndNum) {
        if (row < 0 || row > rowEndNum || cell < 0 || cell > cellEndNum ||
                sheet.getRow(row).getCell(cell).getStringCellValue() == "visited") {
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
            } catch (Exception e) {
                continue;
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

            File f = new File(MY_FILE);
            FileInputStream excelFile = new FileInputStream(f.getCanonicalPath());

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // JSON data
            JSONObject tableJSON = new JSONObject();


            int keyValueCount = 0;
            int tableCount = 0;
            int cellEnd = 0;
            int rowEnd = sheet.getLastRowNum();
            for (int i = 0; i <= rowEnd; ++i) {
                try {
                    cellEnd = cellEnd > sheet.getRow(i).getLastCellNum() ? cellEnd : sheet.getRow(i).getLastCellNum();
                } catch (Exception e) {
                    continue;
                }
            }

            // store the original cell values in JSON
            for (int rowIndex = 0; rowIndex <= rowEnd; ++rowIndex) {
                JSONObject rowData = new JSONObject();
                for (int cellIndex = 0; cellIndex <= cellEnd; ++cellIndex) {
                    try {
                        if (sheet.getRow(rowIndex).getCell(cellIndex).getCellTypeEnum() == CellType.STRING) {
                            rowData.put(String.valueOf(cellIndex), sheet.getRow(rowIndex).getCell(cellIndex).getStringCellValue());
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
                JSONArray cellArray = new JSONArray();
                cellArray.put(rowData);
                try {
                    tableJSON.put(String.valueOf(rowIndex), cellArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            for (int rowIndex = 0; rowIndex <= rowEnd; ++rowIndex) {
                for (int cellIndex = 0; cellIndex <= cellEnd; ++cellIndex) {
                    try {
                        if (sheet.getRow(rowIndex).getCell(cellIndex).getCellTypeEnum() == CellType.STRING) {
                            if (exploreTable(sheet, rowIndex, cellIndex, rowEnd, cellEnd)) {
                                ++tableCount;
                            }
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }

            log.info("Getting file: "+ f.getCanonicalPath());
            JSONObject finalJSON = new JSONObject();
            try {
                finalJSON.put("data", tableJSON);
                finalJSON.put("tableCount", tableCount);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(finalJSON);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
