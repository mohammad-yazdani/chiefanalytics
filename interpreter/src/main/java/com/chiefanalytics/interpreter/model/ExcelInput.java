package com.chiefanalytics.interpreter.model;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ExcelInput extends InputFile {

    private static Logger log = LoggerFactory.getLogger(ExcelInput.class);

    public ExcelInput (String abspath) {

        try {
            this.data = new XSSFWorkbook(abspath);
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public File publish() {
        return null;
    }
}
