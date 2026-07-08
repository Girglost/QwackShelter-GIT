package qwack_boot.api.requestDTO.statutAnimal;

import jakarta.validation.constraints.NotNull;

public record CreateStatutAnimalRequest 
    (
        @NotNull Integer animalId,
        @NotNull Integer emplacementId
    )    
{}

