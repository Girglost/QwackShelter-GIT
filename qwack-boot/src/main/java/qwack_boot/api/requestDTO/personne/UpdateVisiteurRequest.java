package qwack_boot.api.requestDTO.personne;

import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;

public class UpdateVisiteurRequest {
    private String nom;
    private String prenom;

    private CreateLieuRequest habitation;
    private Integer quackShelterId;

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

}
