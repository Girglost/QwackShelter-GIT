package qwack_boot.api.requestDTO.statutAnimal;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutValidation;

public record UpdateStatutAnimalRequest 
    (
        @NotBlank LocalDate dateArrivee,
        LocalDate dateDepart,
        @NotNull Statut statut,
        StatutValidation statutAdoption,
        @NotNull Integer emplacementId,
        Integer adoptantId,
        @NotNull Integer animalId
)


{}
