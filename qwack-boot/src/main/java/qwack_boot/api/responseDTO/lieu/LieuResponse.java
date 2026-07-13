package qwack_boot.api.responseDTO.lieu;

import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;
import qwack_boot.model.TypeLieu;

public class LieuResponse {

    private Integer id;
    private TypeLieu typeLieu;
    private Adresse adresse;

    public LieuResponse() {
    }

    public static LieuResponse convert(Lieu lieu) {

        if (lieu == null) {
            return null;
        }
        LieuResponse response = new LieuResponse();

        response.setId(lieu.getId());
        response.setTypeLieu(lieu.getType());
        response.setAdresse(lieu.getAdresse());

        return response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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