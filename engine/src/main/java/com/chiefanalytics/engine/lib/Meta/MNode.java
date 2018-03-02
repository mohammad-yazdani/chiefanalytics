package com.chiefanalytics.engine.lib.Meta;

import com.chiefanalytics.engine.lib.Exception.Logic.NotCommittedOffset;
import com.chiefanalytics.engine.lib.Query.Query;

import java.util.ArrayList;

public abstract class MNode {

    private MNode parent;
    private ArrayList<MNode> offset;

    protected MNode() {
        offset = new ArrayList<>();
    }

    protected void setParent(MNode parent) {
        this.parent = parent;
    }

    protected void addToOffset(MNode mNode) {
        this.offset.add(mNode);
    }

    protected abstract int merge(MNode mNode);

    public abstract MNode branch(Query query);
    public abstract MNode fork(Query query);

    public int commit() throws NotCommittedOffset {
        if (this.offset.size() > 0) throw new NotCommittedOffset();
        if (this.merge(this.parent) > 0) {
            // TODO : Implement exception with tracing
            return -1;
        }
        return 0;
    }

    public abstract boolean isRoot();
}
