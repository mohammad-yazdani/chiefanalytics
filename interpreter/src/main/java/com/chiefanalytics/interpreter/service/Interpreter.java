package com.chiefanalytics.interpreter.service;

import com.chiefanalytics.interpreter.model.InputFile;

/*
This class is the parent decorator class.
 */
public abstract class Interpreter extends InputFile {

    InputFile inputFile;

    protected abstract void interpret ();

}
