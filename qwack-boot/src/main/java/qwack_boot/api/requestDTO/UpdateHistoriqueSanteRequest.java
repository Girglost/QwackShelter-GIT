package qwack_boot.api.requestDTO;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import qwack_boot.model.Cause;

public record UpdateHistoriqueSanteRequest (@NotBlank String commentaire,
    @Positive double poids,
    @NotNull LocalDate date,
    @NotNull LocalTime heure,
    @Positive int duree,
    @NotNull Cause cause,
    @NotNull Integer animalId){

}
