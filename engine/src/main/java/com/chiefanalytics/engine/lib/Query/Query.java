package com.chiefanalytics.engine.lib.Query;

import java.util.ArrayList;

abstract public class Query {

    private ArrayList<String> tokenList;

    Query(String head) {
        this.tokenList = new ArrayList<>();
        this.tokenList.add(head);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(this.tokenList.get(0));
        for (String token : this.tokenList) {
            out.append(" ").append(token);
        }
        return out.toString();
    }

    public void push(String token) {
        this.tokenList.add(token);
    }

    public String pop() {
        int oldLen = this.tokenList.size();
        return this.tokenList.remove(oldLen - 1);
    }
}
