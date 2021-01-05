package com.CSI5324.model;

public enum EdgeCategory {
    IS_A("IS_A"),
    HAS_A("HAS_A");


    public final String label;

    private EdgeCategory(String label){
        this.label = label;
    }
}
