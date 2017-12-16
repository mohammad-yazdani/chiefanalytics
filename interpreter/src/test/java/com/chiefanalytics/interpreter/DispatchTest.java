package com.chiefanalytics.interpreter;

import com.chiefanalytics.interpreter.service.dispatch.ViewDispatch;
import org.junit.Test;

import java.util.Random;

public class DispatchTest {

    @Test
    public void streamTest() {
        Random rand = new Random();
        new ViewDispatch().send("test message" + rand.nextInt(99999));
    }
}
