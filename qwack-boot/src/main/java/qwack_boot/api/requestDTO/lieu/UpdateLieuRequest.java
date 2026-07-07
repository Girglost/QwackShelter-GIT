package qwack_boot.api.requestDTO.lieu;

import jakarta.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import jakarta.validation.Valid;
import qwack_boot.model.TypeLieu;
import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;

public class UpdateLieuRequest {

private Integer id;

    @NotNull
    private TypeLieu type;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

}
