package com.chiefanalytics.interpreter.parse;

import java.util.HashMap;
import java.util.Map;

public class TableParse extends Parser {

    private class GenericSet {

        private Map<Integer, Integer> store;

        private int horizontal;

        private int vertical;

        public GenericSet() {
            this.horizontal = 0;
            this.vertical = 0;
            this.store = new HashMap<>();
        }

        public void add(int row, int col) {
            this.store.put(row, col);
            if (this.vertical < row) this.vertical++;
            if (this.horizontal < col) this.horizontal++;
        }
    }

    @Override
    public void Parse() {

    }
}
