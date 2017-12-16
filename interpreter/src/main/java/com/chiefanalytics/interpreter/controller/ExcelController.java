package com.chiefanalytics.interpreter.controller;

import com.chiefanalytics.interpreter.service.dispatch.ViewDispatch;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class ExcelController implements Controller {

    private static Logger log = LoggerFactory.getLogger(ExcelController.class);

    private final
    ViewDispatch viewDispatch;

    @Autowired
    public ExcelController(ViewDispatch viewDispatch) {
        this.viewDispatch = viewDispatch;
    }

    @Override
    public String getBase() {
        System.out.print(" Excel class implementing getBase()");
        return null;
    }

    @Override
    public String getHeader() {
        System.out.print(" Excel class implementing getHeader()");
        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        try {
            InputStream initialStream = file.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            if (initialStream.read(buffer) < 1) throw new IOException("Empty file uploaded.");

            File localFile = new File(name);
            OutputStream outStream = new FileOutputStream(localFile);
            outStream.write(buffer);
            FileInputStream excelFile = new FileInputStream(localFile);

            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("name", name);
            jsonPayload.put("file", localFile.getPath());

            viewDispatch.send(jsonPayload.toJSONString());

            log.info(excelFile.toString());
            log.info("Getting file: "+ localFile.getCanonicalPath());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
