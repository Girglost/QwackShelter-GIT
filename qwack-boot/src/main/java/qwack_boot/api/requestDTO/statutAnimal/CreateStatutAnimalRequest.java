package qwack_boot.api.requestDTO.statutAnimal;

import jakarta.validation.constraints.NotNull;

public class CreateStatutAnimalRequest {
    @NotNull
    Integer animalId;
    @NotNull
    Integer emplacementId;

    public Integer getAnimalId() {
        return animalId;
    }
    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }
    public Integer getEmplacementId() {
        return emplacementId;
    }
    public void setEmplacementId(Integer emplacementId) {
        this.emplacementId = emplacementId;
    }

    
}
