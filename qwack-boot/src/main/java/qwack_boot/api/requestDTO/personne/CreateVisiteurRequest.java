package qwack_boot.api.requestDTO.personne;

import jakarta.validation.constraints.NotBlank;
import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;

public class CreateVisiteurRequest {
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    // Pour la modification, on ne modifie pas le login
    String login;
    @NotBlank
    String password;
    @NotBlank
    CreateLieuRequest habitation;
    @NotBlank
    Integer quackShelterId;

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

    @Override
    public String toString() {
        return "CreateVisiteurRequest [nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
                + password + ", habitation=" + habitation + ", quackShelterId=" + quackShelterId + "]";
    }

}
