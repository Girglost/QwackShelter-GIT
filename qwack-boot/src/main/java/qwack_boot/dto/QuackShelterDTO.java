package qwack_boot.dto;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.QuackShelter;

public class QuackShelterDTO {
    private Integer id;
    private LieuDTO lieu;

    public static QuackShelterDTO convert(QuackShelter quackShelter) {
        QuackShelterDTO q = new QuackShelterDTO();
        BeanUtils.copyProperties(quackShelter, q);
        q.lieu = LieuDTO.convert(quackShelter.getLieu());
        return q;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LieuDTO getLieu() {
        return lieu;
    }

    public void setLieu(LieuDTO lieu) {
        this.lieu = lieu;
    }

}
