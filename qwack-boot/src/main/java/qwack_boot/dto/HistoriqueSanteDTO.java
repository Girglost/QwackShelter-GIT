package qwack_boot.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Cause;
import qwack_boot.model.HistoriqueSante;

public class HistoriqueSanteDTO {

    private Integer id;
    private String commentaire;
    private double poids;
    private LocalDate date;
    private LocalTime heure;
    private int duree;
    private Cause cause;

    private Integer idAnimal;
    private String nomAnimal;

    public static HistoriqueSanteDTO convert(HistoriqueSante historique) {
        HistoriqueSanteDTO dto = new HistoriqueSanteDTO();
        BeanUtils.copyProperties(historique, dto);

        dto.idAnimal = historique.getAnimal().getId();
        dto.nomAnimal = historique.getAnimal().getNomAnimal();

        return dto;
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

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }
}