package com.chiefanalytics.engine.lib.Query;

abstract public class Query {

    private String[] tokenList;

    Query(String[] tokens) {
        this.tokenList = tokens;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(this.tokenList[0]);
        for (String token : this.tokenList) {
            out.append(" ").append(token);
        }
        return out.toString();
    }
}
