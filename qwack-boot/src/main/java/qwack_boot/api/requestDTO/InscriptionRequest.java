package qwack_boot.api.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;
import qwack_boot.model.Role;

public class InscriptionRequest {
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    // Pour la modification, on ne modifie pas le login
    String login;
    @NotBlank
    String password;
    @NotNull
    CreateLieuRequest habitation;
    @NotNull
    Integer quackShelterId;
    @NotNull
    Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
