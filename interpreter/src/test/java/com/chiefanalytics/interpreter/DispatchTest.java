package com.chiefanalytics.interpreter;

import com.chiefanalytics.interpreter.model.ExcelInput;
import com.chiefanalytics.interpreter.service.dispatch.ViewDispatch;
import org.junit.Test;

import java.io.File;

public class DispatchTest {

    @Test
    public void streamTest() {
        // TODO : Open socket on a 5 digit port
        // TODO : Stream to the socket
        // TODO : Assure value
        String output = (new ViewDispatch().send(new ExcelInput(new File(""))));
        assert (output.length() > 0);
    }
}
