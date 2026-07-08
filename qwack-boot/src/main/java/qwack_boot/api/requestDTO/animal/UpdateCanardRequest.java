package qwack_boot.api.requestDTO.animal;

import java.util.List;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Canard;
import qwack_boot.model.Caractere;
import qwack_boot.model.Famille;
import qwack_boot.model.Genre;

public class UpdateCanardRequest {

    private String nomAnimal;
    private String couleur;
    private String regimeAlimentaire;
    private String traitement;
    private Famille famille;
    private Genre genre;

    private List<Caractere> caracteres;

    private Integer qwackShelterId;

    private boolean capaciteVol;
    private boolean pondeuse;

    private String race;
    private boolean estSauvage;


    
    public List<Caractere> getCaracteres() {
        return caracteres;
    }



    public void setCaracteres(List<Caractere> caracteres) {
        this.caracteres = caracteres;
    }



    public Integer getQwackShelterId() {
        return qwackShelterId;
    }



    public void setQwackShelterId(Integer qwackShelterId) {
        this.qwackShelterId = qwackShelterId;
    }



    public void setEstSauvage(boolean estSauvage) {
        this.estSauvage = estSauvage;
    }

    public boolean isEstSauvage() {
        return estSauvage;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }
    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public void setRegimeAlimentaire(String regimeAlimentaire) {
        this.regimeAlimentaire = regimeAlimentaire;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    public boolean isCapaciteVol() {
        return capaciteVol;
    }

    public void setCapaciteVol(boolean capaciteVol) {
        this.capaciteVol = capaciteVol;
    }

    public boolean isPondeuse() {
        return pondeuse;
    }

    public void setPondeuse(boolean pondeuse) {
        this.pondeuse = pondeuse;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
