package com.example.coursfsr;

public class Cours {
    private String name;
    private int image;
    private int numCours;

    public Cours(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public Cours(String name, int image, int numCours) {
        this.name = name;
        this.image = image;
        this.numCours = numCours;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getNumCours() {
        return numCours;
    }

    public void setNumCours(int numCours) {
        this.numCours = numCours;
    }
}
