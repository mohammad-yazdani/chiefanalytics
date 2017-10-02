package com.chiefanalytics.interpreter.service;

import com.chiefanalytics.interpreter.model.InputFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

public class TableInterpreter extends Interpreter {

    @Override
    protected void interpret() {
        this.inputFile.data = this.tableInterpret(this.inputFile);
    }

    private XSSFWorkbook tableInterpret (XSSFWorkbook workbook) {

        // TODO : Just to have some change
        workbook.setActiveSheet(0);

        return workbook;
    }

    // TODO : Template for other file types
    private InputFile tableInterpret (Object object) { return null;}

    @Override
    public File publish() {
        return null;
    }
}
