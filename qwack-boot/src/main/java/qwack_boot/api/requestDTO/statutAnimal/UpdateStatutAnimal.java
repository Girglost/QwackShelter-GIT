package qwack_boot.api.requestDTO.statutAnimal;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import qwack_boot.model.Statut;

public class UpdateStatutAnimal {
    @NotBlank
    LocalDate dateArrivee;
    LocalDate dateDepart;
    @NotNull
    Statut statut;
    @NotNull
    Integer emplacementId;
    Integer adoptantId;
    
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
    public Integer getEmplacementId() {
        return emplacementId;
    }
    public void setEmplacementId(Integer emplacementId) {
        this.emplacementId = emplacementId;
    }
    public Integer getAdoptantId() {
        return adoptantId;
    }
    public void setAdoptantId(Integer adoptantId) {
        this.adoptantId = adoptantId;
    }

    

}
