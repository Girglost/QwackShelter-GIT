package qwack_boot.api.requestDTO;

public class AdoptionRequest {
    Integer idQuackShelter;
    Integer idPersonne;
    Integer idAnimal;

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
