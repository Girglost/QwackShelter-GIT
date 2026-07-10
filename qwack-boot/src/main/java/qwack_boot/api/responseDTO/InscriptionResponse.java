package qwack_boot.api.responseDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import qwack_boot.api.responseDTO.lieu.LieuResponse;
import qwack_boot.model.Personne;
import qwack_boot.model.Role;

public class InscriptionResponse {

    Integer id;
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    // Pour la modification, on ne modifie pas le login
    String login;
    @NotNull
    LieuResponse habitation;
    @NotNull
    Integer quackShelterId;
    @NotNull
    Role role;

    LocalDate dateInscription;

    public static InscriptionResponse convert(Personne personne) {
        InscriptionResponse sub = new InscriptionResponse();
        sub.setDateInscription(personne.getDateInscription());
        sub.setNom(personne.getNom());
        sub.setPrenom(personne.getPrenom());
        sub.setLogin(personne.getLogin());
        sub.setHabitation(LieuResponse.convert(personne.getHabitation()));
        sub.setQuackShelterId(personne.getQuackShelter().getId());
        sub.setRole(personne.getRole());
        return sub;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LieuResponse getHabitation() {
        return habitation;
    }

    public void setHabitation(LieuResponse habitation) {
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

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

}
