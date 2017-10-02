package com.chiefanalytics.interpreter.service;

import com.chiefanalytics.interpreter.model.InputFile;

/*
This class is the parent decorator class.
 */
public abstract class Interpreter extends InputFile {

    protected InputFile inputFile;

    protected abstract byte[] interpret ();

}
