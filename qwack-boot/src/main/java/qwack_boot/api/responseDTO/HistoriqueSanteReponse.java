package qwack_boot.api.responseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

import qwack_boot.model.Cause;
import qwack_boot.model.HistoriqueSante;

public class HistoriqueSanteReponse {
    private Integer id;
    private String commentaire;
    private double poids;
    private LocalDate date;
    private LocalTime heure;
    private int duree;
    private Cause cause;
    private Integer animalId;

    public static HistoriqueSanteReponse convert(HistoriqueSante hSante){
        HistoriqueSanteReponse hs = new HistoriqueSanteReponse();
        hs.id = hSante.getId();
        hs.commentaire = hSante.getCommentaire();
        hs.poids = hSante.getPoids();
        hs.date = hSante.getDate();
        hs.heure = hSante.getHeure();
        hs.duree = hSante.getDuree();
        hs.cause = hSante.getCause();
        hs.animalId = hSante.getAnimal().getId();

        return hs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    

}
