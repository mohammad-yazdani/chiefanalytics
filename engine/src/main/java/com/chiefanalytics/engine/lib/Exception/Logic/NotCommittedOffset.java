package com.chiefanalytics.engine.lib.Exception.Logic;

public class NotCommittedOffset extends Exception {

    // TODO : Implement tracing offset

    @Override
    public String getMessage() {
        String logicError = "Trying to commit MNode with active offset.\n";
        return logicError + super.getMessage();
    }
}
