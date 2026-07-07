package qwack_boot.api.requestDTO.lieu;

import org.springframework.beans.BeanUtils;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import qwack_boot.model.TypeLieu;
import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;

public class CreateLieuRequest {

    @NotNull
    private TypeLieu type;

    @Valid
    @NotNull
    private Adresse adresse;

        public static CreateLieuRequest convert(Lieu lieu) {
        CreateLieuRequest l = new CreateLieuRequest();
        BeanUtils.copyProperties(lieu, l);
        return l;
    }

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

