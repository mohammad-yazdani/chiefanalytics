package com.chiefanalytics.interpreter.parse;

import com.chiefanalytics.interpreter.model.InputFile;

public abstract class Parser extends InputFile {

    private InputFile inputFile;

    public abstract void Parse();
}
