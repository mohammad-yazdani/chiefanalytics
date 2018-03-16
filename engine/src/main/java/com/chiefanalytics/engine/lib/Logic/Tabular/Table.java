package com.chiefanalytics.engine.lib.Logic.Tabular;

import com.chiefanalytics.engine.lib.Meta.MNode;
import com.chiefanalytics.engine.lib.Query.Query;

public class Table extends MNode {

    private String[][] data;
    private boolean isRoot;

    private Table(Table parent) {
        this.data = null;
        this.isRoot = false;
        this.setParent(parent);
    }

    private Table(String[][] data) {
        this.data = data;
        isRoot = true;
    }

    @Override
    protected int merge(MNode mNode) {
        return 0;
    }

    @Override
    public MNode branch(Query query) {
        MNode child = new Table(this);
        this.addToOffset(child);
        // TODO : Based on query, store the queried information on a new MNode and return it.
        return child;
    }

    @Override
    public MNode fork(Query query) {
        // TODO : Based on query, store the queried information on a new MNode and return it.
        return new Table(this.data);
    }

    @Override
    public boolean isRoot() {
        return this.isRoot;
    }
}
