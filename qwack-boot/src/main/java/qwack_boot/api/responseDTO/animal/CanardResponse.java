package qwack_boot.api.responseDTO.animal;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Canard;
import qwack_boot.model.Famille;
import qwack_boot.model.Genre;

public class CanardResponse {

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
    private boolean estSauvage;
    private String description;

    public Integer getQwackShelterId() {
        return qwackShelterId;
    }

    public void setQwackShelterId(Integer qwackShelterId) {
        this.qwackShelterId = qwackShelterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static CanardResponse convert(Canard canard) {
        CanardResponse p = new CanardResponse();

        BeanUtils.copyProperties(canard, p);

        p.setQwackShelterId(canard.getQuackShelter().getId());

        return p;
    }

    public void setEstSauvage(boolean estSauvage) {
        this.estSauvage = estSauvage;
    }

    public boolean isEstSauvage() {
        return estSauvage;
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