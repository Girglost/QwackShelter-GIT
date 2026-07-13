package qwack_boot.api.requestDTO.lieu;

import org.springframework.beans.BeanUtils;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;
import qwack_boot.model.TypeLieu;

public class UpdateLieuRequest {

    private Integer id;

    @NotNull
    private TypeLieu typeLieu;

    @Valid
    @NotNull
    private Adresse adresse;

    public static UpdateLieuRequest convert(Lieu lieu) {
        UpdateLieuRequest l = new UpdateLieuRequest();
        BeanUtils.copyProperties(lieu, l);
        return l;
    }

    public UpdateLieuRequest() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
