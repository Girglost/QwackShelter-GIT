package qwack_boot.api.requestDTO.personne;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;
import qwack_boot.model.StatutActivite;

public class UpdateBenevoleRequest {
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    @NotBlank
    String password;
    @NotNull
    CreateLieuRequest habitation;
    @NotNull
    Integer quackShelterId;
    @NotNull
    StatutActivite statutActivite;

    public StatutActivite getStatutActivite() {
        return statutActivite;
    }

    public void setStatutActivite(StatutActivite statutActivite) {
        this.statutActivite = statutActivite;
    }

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

    @Override
    public String toString() {
        return "UpdateBenevoleRequest [nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", habitation="
                + habitation + ", quackShelterId=" + quackShelterId + "]";
    }
}
