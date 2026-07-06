package qwack_boot.api.requestDTO;

import qwack_boot.model.StatutAnimal;

public class AdoptionRequest {
    Integer idQuackShelter;
    Integer idPersonne;
    Integer idAnimal;

    public static AdoptionRequest convert(StatutAnimal statutAnimal) {
        AdoptionRequest a = new AdoptionRequest();
        a.setIdPersonne(statutAnimal.getAdoptant().getId());
        a.setIdAnimal(statutAnimal.getAnimal().getId());
        a.setIdQuackShelter(statutAnimal.getAnimal().getQuackShelter().getId());
        return a;

    }

    public Integer getIdQuackShelter() {
        return idQuackShelter;
    }

    public void setIdQuackShelter(Integer idQuackShelter) {
        this.idQuackShelter = idQuackShelter;
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

}
