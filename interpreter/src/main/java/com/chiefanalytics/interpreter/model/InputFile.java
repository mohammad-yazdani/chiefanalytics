package com.chiefanalytics.interpreter.model;

public abstract class InputFile {

    /*
    This represent the absolute path of file on file system.
     */
    protected String abspath;

    public abstract byte[] getData();
}
