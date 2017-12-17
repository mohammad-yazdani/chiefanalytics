package com.chiefanalytics.interpreter.parse;

import com.chiefanalytics.interpreter.model.ExcelInput;

import java.util.HashMap;
import java.util.Map;

public class TableParse extends Parser {

    public TableParse(ExcelInput inputFile) {
        super(inputFile);
    }

    private class Coords {
        int row;
        int col;

        Coords(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coords)) return false;

            Coords coords = (Coords) o;

            return row == coords.row && col == coords.col;
        }
    }

    private class GenericSet {

        private Map<Coords, Boolean> store;

        private int horizontal;

        private int vertical;

        public GenericSet() {
            this.horizontal = 0;
            this.vertical = 0;
            this.store = new HashMap<>();
        }

        public Boolean add(int row, int col, Boolean val) {
            Coords coords = new Coords(row, col);
            if (!this.store.get(coords)) {
                this.store.put(coords, val);
                if (this.vertical < row) this.vertical++;
                if (this.horizontal < col) this.horizontal++;
                return true;
            } else {
                return false;
            }
        }

        public String getType() {
            // TODO : Implement
            return null;
        }
    }

    @Override
    public void Parse() {
        // TODO : Make a list of generic sets
    }

    public int cruise(GenericSet set, int row, int col) {
        // TODO : Does the first cell exist?
        // At the end return the index of next available (using library)
        return 0;
    }
}
