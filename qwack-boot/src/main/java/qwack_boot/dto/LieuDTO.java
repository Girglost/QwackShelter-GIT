package qwack_boot.dto;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;
import qwack_boot.model.TypeLieu;

public class LieuDTO {
    private Integer id;
    private TypeLieu type;
    private Adresse adresse;

    public static LieuDTO convert(Lieu lieu) {
        LieuDTO l = new LieuDTO();
        BeanUtils.copyProperties(lieu, l);
        return l;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
