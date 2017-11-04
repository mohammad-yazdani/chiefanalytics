package com.chiefanalytics.interpreter.service.dispatch;

import com.chiefanalytics.interpreter.model.InputFile;

public class ViewDispatch extends Dispatch {

    public ViewDispatch() {
        super("view-service");
    }

    @Override
    public String send(InputFile payload) {
        return this.service + payload.toString();
    }
}
