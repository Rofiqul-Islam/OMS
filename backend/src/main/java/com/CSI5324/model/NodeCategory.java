package com.CSI5324.model;

public enum NodeCategory {
    BuildingComponent("Builidng Component"),
    Nutrition("Nutrition"),
    HealthConcern("Health Concern");

    public final String label;

    private NodeCategory(String label){
        this.label = label;
    }
}
