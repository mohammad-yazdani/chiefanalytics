package com.chiefanalytics.interpreter.parse;

import com.chiefanalytics.interpreter.model.InputFile;

public abstract class Parser {

    protected InputFile inputFile;

    public abstract void Parse();

    public Parser(InputFile inputFile) {
        this.inputFile = inputFile;
    }
}
