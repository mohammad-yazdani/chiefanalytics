package com.chiefanalytics.interpreter.controller;

public class PdfController implements Controller{

    @Override
    public String getBase() {
        System.out.print(" Pdf class implementing getBase()");
        return null;
    }

    @Override
    public String getHeader() {
        System.out.print(" Pdf class implementing getHeader()");
        return null;
    }
}
