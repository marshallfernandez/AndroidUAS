package com.puntobat.uas.model;

/**
 * Created by edu24 on 26/06/2016.
 */
public class SpecialtyConf {
    private int id;
    private String aceptThreshold;
    private String levelExpect;
    private String criteriaLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAceptThreshold() {
        return aceptThreshold;
    }

    public void setAceptThreshold(String aceptThreshold) {
        this.aceptThreshold = aceptThreshold;
    }

    public String getLevelExpect() {
        return levelExpect;
    }

    public void setLevelExpect(String levelExpect) {
        this.levelExpect = levelExpect;
    }

    public String getCriteriaLevel() {
        return criteriaLevel;
    }

    public void setCriteriaLevel(String criteriaLevel) {
        this.criteriaLevel = criteriaLevel;
    }
}
