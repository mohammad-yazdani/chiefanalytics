package com.chiefanalytics.interpreter.model;

import java.io.File;

public abstract class InputFile {

    /*
    This represent the absolute path of file on file system.
     */
    public Object data;

    public abstract File publish();
}
