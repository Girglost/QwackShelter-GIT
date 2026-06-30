package qwack_boot.dto.personne;

import org.springframework.beans.BeanUtils;

import qwack_boot.dto.LieuUpdateDTO;
import qwack_boot.model.Personne;

public class VisiteurUpdateDTO {
    private String nom;
    private String prenom;
    private String password;
    private LieuUpdateDTO habitation;

    public static VisiteurUpdateDTO convert(Personne personne) {
        VisiteurUpdateDTO v = new VisiteurUpdateDTO();
        BeanUtils.copyProperties(personne, v);
        return v;
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

    public LieuUpdateDTO getHabitation() {
        return habitation;
    }

    public void setHabitation(LieuUpdateDTO habitation) {
        this.habitation = habitation;
    }
}
