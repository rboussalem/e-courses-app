package com.example.coursfsr;

public class iteamNote {

    private String module;
    private String titreModule;
    private double moduleNote;

    public iteamNote(String module, String titreModule, double moduleNote) {
        this.module = module;
        this.titreModule = titreModule;
        this.moduleNote = moduleNote;
    }

    public String getModule() {
        return module;
    }

    public String getTitreModule() {
        return titreModule;
    }

    public double getModuleNote() {
        return moduleNote;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setTitreModule(String titreModule) {
        this.titreModule = titreModule;
    }

    public void setModuleNote(double moduleNote) {
        this.moduleNote = moduleNote;
    }
}
