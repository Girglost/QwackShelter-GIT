package qwack_boot.api.responseDTO;

import qwack_boot.model.Emplacement;
import qwack_boot.model.typeBox;

public class EmplacementReponse {
    private Integer id;
    private int nb_place;
    private boolean complet;
    private typeBox box;
    
    public static EmplacementReponse convert(Emplacement emp){
        EmplacementReponse e = new EmplacementReponse();

        e.id = emp.getId();
        e.nb_place = emp.getNbPlace();
        e.complet = emp.isComplet();
        e.box = emp.getBox();

        return e;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public boolean isComplet() {
        return complet;
    }

    public void setComplet(boolean complet) {
        this.complet = complet;
    }

    public typeBox getBox() {
        return box;
    }

    public void setBox(typeBox box) {
        this.box = box;
    }

    
}
