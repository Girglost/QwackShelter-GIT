package qwack_boot.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Visite;

public class VisiteDTO {

    private Integer id;
    private LocalDateTime dateVisite;

    private Integer idVisiteur;
    private String nomVisiteur;

    private Integer idAnimal;
    private String nomAnimal;

    private Integer idQuackShelter;

    public static VisiteDTO convert(Visite visite) {
        VisiteDTO dto = new VisiteDTO();
        BeanUtils.copyProperties(visite, dto);

        dto.idVisiteur = visite.getVisiteur().getId();
        dto.nomVisiteur = visite.getVisiteur().getNom();

        dto.idAnimal = visite.getAnimal().getId();
        dto.nomAnimal = visite.getAnimal().getNomAnimal();

        dto.idQuackShelter = visite.getQuackShelter().getId();

        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDateTime dateVisite) {
        this.dateVisite = dateVisite;
    }

    public Integer getIdVisiteur() {
        return idVisiteur;
    }

    public void setIdVisiteur(Integer idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    public String getNomVisiteur() {
        return nomVisiteur;
    }

    public void setNomVisiteur(String nomVisiteur) {
        this.nomVisiteur = nomVisiteur;
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

    public Integer getIdQuackShelter() {
        return idQuackShelter;
    }

    public void setIdQuackShelter(Integer idQuackShelter) {
        this.idQuackShelter = idQuackShelter;
    }
}