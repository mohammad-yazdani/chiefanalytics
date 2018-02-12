package com.chiefanalytics.engine.lib.Base.Model;

import com.chiefanalytics.engine.lib.Base.Ops.Operation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Queryable<T> implements Operation {

    private static Logger log = LoggerFactory.getLogger(Queryable.class);

    @Autowired
    private ObjectMapper Json;

    private T[][] data;

    Queryable(T[][] data) {
        this.data = data;
    }

    Queryable(String json) {
        T[][] data;
        try {
            data = (T[][]) Json.readValue(json, Object.class);
        } catch (JsonParseException e) {
            log.error("JSON not parsable.");
            log.error(e.getMessage());
            data = null;
        } catch (JsonMappingException e) {
            log.error("Could not map JSON to Object class");
            log.error(e.getMessage());
            data = null;
        } catch (IOException e) {
            log.error(e.getMessage());
            data = null;
        }
        this.data = data;
    }

    // TODO : Very slow rn
    // TODO : Unit test
    public Queryable<T> sub(int x, int y, int height, int width) {
        T[][] subset = (T[][]) new Object[height][width];
        for (int r = y; r < height; r++) {
            System.arraycopy(this.data[r], x, subset[r - y], 0, width);
        }

        return new Queryable<>(subset);
    }
}
