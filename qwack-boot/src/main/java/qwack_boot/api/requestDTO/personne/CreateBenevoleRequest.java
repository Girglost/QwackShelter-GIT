package qwack_boot.api.requestDTO.personne;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;

public class CreateBenevoleRequest {
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    @NotBlank
    String login;
    @NotBlank
    String password;
    @NotNull
    CreateLieuRequest habitation;
    @NotNull
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

    public Integer getQuackShelterId() {
        return quackShelterId;
    }

    public void setQuackShelterId(Integer quackShelterId) {
        this.quackShelterId = quackShelterId;
    }

    public CreateLieuRequest getHabitation() {
        return habitation;
    }

    public void setHabitation(CreateLieuRequest habitation) {
        this.habitation = habitation;
    }

    @Override
    public String toString() {
        return "CreateBenevoleRequest [nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
                + password + ", habitation=" + habitation + ", quackShelterId=" + quackShelterId + "]";
    }

}
