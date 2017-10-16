package com.chiefanalytics.interpreter.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

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

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        try {
            InputStream initialStream = file.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            if (initialStream.read(buffer) < 1) throw new IOException("Empty file uploaded.");

            File localFile = new File(name);
            OutputStream outStream = new FileOutputStream(localFile);
            outStream.write(buffer);
            FileInputStream excelFile = new FileInputStream(localFile);

            log.info("Getting file: "+ localFile.getCanonicalPath());

            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet dataTypeSheet = workbook.getSheetAt(0);

            for (Row currentRow : dataTypeSheet) {
                for (Cell currentCell : currentRow) {
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        log.info(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        log.info(currentCell.getNumericCellValue() + "--");
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
