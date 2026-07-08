package qwack_boot.api.requestDTO.animal;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import qwack_boot.model.Caractere;
import qwack_boot.model.Famille;
import qwack_boot.model.Genre;

public class CreateAnimalRequest {

    @NotBlank
    private String nomAnimal;
    private LocalDate dateNaissance;
    private String couleur;
    private String regimeAlimentaire;
    private String traitement;
    @NotBlank
    private Famille famille;
    private Genre genre;

  

    private List<Caractere> caracteres;

    @NotBlank
    private Integer qwackShelterId;

    private boolean capaciteVol;
    private boolean pondeuse;

    private String race;
    private boolean sterilisation;
    private boolean gestante;
    private boolean estSauvage;  
    private String espece;

    
    public boolean isSterilisation() {
        return sterilisation;
    }

    public void setSterilisation(boolean sterilisation) {
        this.sterilisation = sterilisation;
    }

    public boolean isGestante() {
        return gestante;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
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

    public Integer getQwackShelterId() {
        return qwackShelterId;
    }

    public void setQwackShelterId(Integer qwackShelterId) {
        this.qwackShelterId = qwackShelterId;
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

    public List<Caractere> getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(List<Caractere> caracteres) {
        this.caracteres = caracteres;
    }

}
