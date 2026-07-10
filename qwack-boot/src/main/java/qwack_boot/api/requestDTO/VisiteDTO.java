package qwack_boot.api.requestDTO;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import qwack_boot.model.StatutValidation;
import qwack_boot.model.Visite;

public class VisiteDTO {

    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateVisite;

    private Integer idVisiteur;
    private String nomVisiteur;

    private Integer idAnimal;
    private String nomAnimal;

    private Integer idQuackShelter;

    private StatutValidation statutVisite;

    public static VisiteDTO convert(Visite visite) {
        VisiteDTO dto = new VisiteDTO();
        BeanUtils.copyProperties(visite, dto);

        dto.idVisiteur = visite.getVisiteur().getId();
        dto.nomVisiteur = visite.getVisiteur().getNom();

        dto.idAnimal = visite.getAnimal().getId();
        dto.nomAnimal = visite.getAnimal().getNomAnimal();

        dto.idQuackShelter = visite.getQuackShelter().getId();

        dto.statutVisite = visite.getStatutVisite();

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

    public StatutValidation getStatutVisite() {
        return statutVisite;
    }

    public void setStatutVisite(StatutValidation statutVisite) {
        this.statutVisite = statutVisite;
    }

}