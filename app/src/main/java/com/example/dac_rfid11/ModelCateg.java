package com.example.dac_rfid11;

public class ModelCateg {
    private String intitule;
    private Integer index;

    public ModelCateg() {
    }

    public ModelCateg(String intitule, Integer index) {
        this.intitule = intitule;
        this.index = index;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
