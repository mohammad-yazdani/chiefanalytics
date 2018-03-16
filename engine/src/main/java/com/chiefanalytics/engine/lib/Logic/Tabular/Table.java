package com.chiefanalytics.engine.lib.Logic.Tabular;

import com.chiefanalytics.engine.lib.Meta.MNode;
import com.chiefanalytics.engine.lib.Query.Query;

public class Table extends MNode {

    private String[][] data;
    private boolean isRoot;

    private Table(Table parent, String subSet[][]) {
        this.data = subSet;
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

        int startRow = Integer.parseInt(query.pop());
        int startCol = Integer.parseInt(query.pop());
        int endRow = Integer.parseInt(query.pop());
        int endCol = Integer.parseInt(query.pop());

        if (startRow > endRow || startCol > endCol) return null; // TODO : Create exception

        String[][] subSet = new String[endRow - startCol][endCol - endRow];

        for (int i = startRow; i <= endRow; i++) {
            System.arraycopy(this.data[i], startCol, subSet[i - startRow], 0, endCol + 1 - startCol);
        }

        MNode child = new Table(this, subSet);
        this.addToOffset(child);

        return child;
    }

    @Override
    public boolean isRoot() {
        return this.isRoot;
    }
}
