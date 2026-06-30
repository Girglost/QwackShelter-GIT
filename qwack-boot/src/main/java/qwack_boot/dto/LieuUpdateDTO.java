package qwack_boot.dto;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;
import qwack_boot.model.TypeLieu;

public class LieuUpdateDTO {
    private TypeLieu type;
    private Adresse adresse;

    public static LieuUpdateDTO convert(Lieu lieu) {
        LieuUpdateDTO l = new LieuUpdateDTO();
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
        return "LieuUpdateDTO [type=" + type + ", adresse=" + adresse + "]";
    }

}
