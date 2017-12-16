package com.chiefanalytics.interpreter.model;

import java.io.File;
import java.io.InputStream;

public abstract class InputFile {

    private File origin;

    // TODO : Change later
    private Object data;

    private Metadata metadata;

    // TODO : Implement
    public InputStream stringify() {return null;}

    // TODO : Implement super ctor
    public InputFile(File originalFile) {
        this.origin = originalFile;
        this.data = this.extractData();
    }

    abstract Object extractData();

    // TODO : This class is defined for input containing input
    public abstract class Sheet {
        // TODO : Should return cell
        public abstract Object getCell(int row, int col);
    }
}
