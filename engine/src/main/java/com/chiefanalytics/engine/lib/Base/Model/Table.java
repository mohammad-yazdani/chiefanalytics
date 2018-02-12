package com.chiefanalytics.engine.lib.Base.Model;

public class Table<T> extends Queryable<T> {

    Table(T[][] data) {
        super(data);
    }
}
