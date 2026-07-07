package qwack_boot.api.responseDTO.animal;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Famille;
import qwack_boot.model.Genre;
import qwack_boot.model.Poule;

public class CreatePouleRequest {

    private Integer id;
    private String nomAnimal;
    private LocalDate dateNaissance;
    private String couleur;
    private String regimeAlimentaire;
    private String traitement;
    private Famille famille;
    private Genre genre;

    private Integer qwackShelterId;

    private boolean capaciteVol;
    private boolean pondeuse;

    private String race;

    public static CreatePouleRequest convert(Poule poule) {
        CreatePouleRequest p = new CreatePouleRequest();

        BeanUtils.copyProperties(poule, p);

        p.setqwackShelterId(poule.getQuackShelter().getId());

        return p;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
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

    public Integer getqwackShelterId() {
        return qwackShelterId;
    }

    public void setqwackShelterId(Integer idQuackShelter) {
        this.qwackShelterId = idQuackShelter;
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