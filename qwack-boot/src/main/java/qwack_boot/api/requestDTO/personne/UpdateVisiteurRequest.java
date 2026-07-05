package qwack_boot.api.requestDTO.personne;

import qwack_boot.api.requestDTO.LieuRequest;

public class UpdateVisiteurRequest {
    private String nom;
    private String prenom;
    private String password;
    private LieuRequest habitation;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LieuRequest getHabitation() {
        return habitation;
    }

    public void setHabitation(LieuRequest habitation) {
        this.habitation = habitation;
    }

    public Integer getQuackShelterId() {
        return quackShelterId;
    }

    public void setQuackShelterId(Integer quackShelterId) {
        this.quackShelterId = quackShelterId;
    }

}
