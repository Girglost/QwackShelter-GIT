package qwack_boot.api.responseDTO;

import java.time.LocalDate;

import qwack_boot.model.StatutAnimal;

public class AdoptionResponse {
    Integer idAdoptant;
    Integer idAnimal;
    LocalDate dateDepart;

    public static AdoptionResponse convert(StatutAnimal adoption) {
        AdoptionResponse a = new AdoptionResponse();
        a.setIdAdoptant(adoption.getAdoptant().getId());
        a.setIdAnimal(adoption.getAnimal().getId());
        a.setDateDepart(adoption.getDateDepart());

        return a;
    }

    public Integer getIdAdoptant() {
        return idAdoptant;
    }

    public void setIdAdoptant(Integer idAdoptant) {
        this.idAdoptant = idAdoptant;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

}
