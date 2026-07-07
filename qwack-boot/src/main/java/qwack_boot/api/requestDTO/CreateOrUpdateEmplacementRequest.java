package qwack_boot.api.requestDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import qwack_boot.model.typeBox;

public record CreateOrUpdateEmplacementRequest (
    @Positive int nb_place, 
    boolean complet, @NotNull 
    typeBox box){}
