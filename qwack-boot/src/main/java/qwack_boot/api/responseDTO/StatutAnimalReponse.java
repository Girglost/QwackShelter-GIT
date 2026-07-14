package qwack_boot.api.responseDTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import qwack_boot.model.Emplacement;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutAnimal;
import qwack_boot.model.StatutValidation;

public class StatutAnimalReponse {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateArrivee;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDepart;
    private Statut statut;
    private StatutValidation statutAdoption;
    private Emplacement emplacement;
    private Integer adoptantId;
    private Integer animalId;

    public static StatutAnimalReponse convert(StatutAnimal sAnimal){
        StatutAnimalReponse sa = new StatutAnimalReponse();

        sa.id = sAnimal.getId();
        sa.dateArrivee = sAnimal.getDateArrivee();
        sa.dateDepart = sAnimal.getDateDepart();
        sa.statut = sAnimal.getStatut();
        sa.statutAdoption = sAnimal.getStatutAdoption();
        sa.emplacement = sAnimal.getEmplacement();

        sa.adoptantId = null;
        if(sAnimal.getAdoptant()!=null){
            sa.adoptantId = sAnimal.getAdoptant().getId();
        }

        sa.animalId = sAnimal.getAnimal().getId();

        return sa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }

    public Integer getAdoptantId() {
        return adoptantId;
    }

    public void setAdoptantId(Integer adoptantId) {
        this.adoptantId = adoptantId;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public StatutValidation getStatutAdoption() {
        return statutAdoption;
    }

    public void setStatutAdoption(StatutValidation statutAdoption) {
        this.statutAdoption = statutAdoption;
    }

    

}
