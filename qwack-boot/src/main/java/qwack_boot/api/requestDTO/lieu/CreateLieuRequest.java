package qwack_boot.api.requestDTO.lieu;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import qwack_boot.model.TypeLieu;
import qwack_boot.model.Adresse;

public class CreateLieuRequest {

    @NotNull
    private TypeLieu type;

    @Valid
    @NotNull
    private Adresse adresse;

    public CreateLieuRequest() {
    }

    public TypeLieu getType() {
        return type;
    }

    public void setType(TypeLieu type) {
        this.type = type;
    }

    public Adresse getAdresse() {
        return adresse;
    }

     public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}

