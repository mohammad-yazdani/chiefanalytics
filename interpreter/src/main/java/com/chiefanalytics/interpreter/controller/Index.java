package com.chiefanalytics.interpreter.controller;

import com.chiefanalytics.interpreter.service.dispatch.ViewDispatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

    private final
    ViewDispatch viewDispatch;

    @Autowired
    public Index(ViewDispatch viewDispatch) {
        this.viewDispatch = viewDispatch;
    }

    @RequestMapping(value = "/dispatch", method = RequestMethod.GET)
    public String dispatch(@RequestParam String message) {
        viewDispatch.send(message);
        return "Message sent!";
    }
}
