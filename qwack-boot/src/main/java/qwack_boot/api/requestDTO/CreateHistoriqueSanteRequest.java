package qwack_boot.api.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import qwack_boot.model.Cause;

public record CreateHistoriqueSanteRequest (
    @NotBlank String commentaire,
    @Positive double poids,
    @NotNull Cause cause,
    @NotNull Integer animalId

){}
