package qwack_boot.api.requestDTO;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;
import qwack_boot.model.TypeLieu;

public class LieuRequest {
    TypeLieu type;
    Adresse adresse;

    public static LieuRequest convert(Lieu lieu) {
        LieuRequest l = new LieuRequest();
        BeanUtils.copyProperties(lieu, l);
        return l;
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

    @Override
    public String toString() {
        return "LieuDTO [type=" + type + ", adresse=" + adresse + "]";
    }
}
