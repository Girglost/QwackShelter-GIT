package qwack_boot.api.requestDTO.lieu;

import org.springframework.beans.BeanUtils;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;
import qwack_boot.model.TypeLieu;

public class CreateLieuRequest {

    @NotNull
    private TypeLieu typeLieu;

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

    @Override
    public String toString() {
        return "CreateLieuRequest [typeLieu=" + typeLieu + ", adresse=" + adresse + "]";
    }

    public TypeLieu getTypeLieu() {
        return typeLieu;
    }

    public void setTypeLieu(TypeLieu typeLieu) {
        this.typeLieu = typeLieu;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
