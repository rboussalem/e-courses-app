package com.example.coursfsr;

public class Etudiant  {

    private String username;
    private String password;
    private String email;
    private String Nom;
    private String Prenom;
    private String CE;


    public Etudiant(){

    }


    public Etudiant(String username,String password,String email,String Nom,String Prenom, String CE){
        this.username = username;
        this.password = password;
        this.email = email;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.CE = CE;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getCE() {
        return CE;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setCE(String CE) {
        this.CE = CE;
    }
}

