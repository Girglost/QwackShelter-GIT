package qwack_boot.api.requestDTO.personne;

import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;
import qwack_boot.model.StatutActivite;

public class UpdateEmployeRequest {
    String nom;
    String prenom;
    String password;
    CreateLieuRequest habitation;
    Integer quackShelterId;
    boolean admin;
    double salaire;
    StatutActivite statutActivite;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreateLieuRequest getHabitation() {
        return habitation;
    }

    public void setHabitation(CreateLieuRequest habitation) {
        this.habitation = habitation;
    }

    public Integer getQuackShelterId() {
        return quackShelterId;
    }

    public void setQuackShelterId(Integer quackShelterId) {
        this.quackShelterId = quackShelterId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

}
