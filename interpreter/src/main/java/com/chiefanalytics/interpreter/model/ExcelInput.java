package com.chiefanalytics.interpreter.model;

import java.io.File;

public class ExcelInput extends InputFile {
    public ExcelInput(File originalFile) {
        super(originalFile);
    }



    @Override
    Object extractData() {
        return null;
    }
}
