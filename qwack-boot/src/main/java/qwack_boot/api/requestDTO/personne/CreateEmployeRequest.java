package qwack_boot.api.requestDTO.personne;

import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;

public class CreateEmployeRequest {
    String nom;
    String prenom;
    String login;
    String password;
    CreateLieuRequest habitation;
    Integer quackShelterId;
    boolean admin;
    double salaire;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public String toString() {
        return "EmployeResponse [nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password=" + password
                + ", habitation=" + habitation + ", quackShelterId=" + quackShelterId + ", admin=" + admin
                + ", salaire=" + salaire + "]";
    }
}
