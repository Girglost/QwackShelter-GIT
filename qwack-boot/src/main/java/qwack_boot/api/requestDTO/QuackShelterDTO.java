package qwack_boot.api.requestDTO;

import org.springframework.beans.BeanUtils;

import qwack_boot.api.responseDTO.lieu.LieuResponse;
import qwack_boot.model.QuackShelter;

public class QuackShelterDTO {
    private Integer id;
    private LieuResponse lieu;

    public static QuackShelterDTO convert(QuackShelter quackShelter) {
        QuackShelterDTO q = new QuackShelterDTO();
        BeanUtils.copyProperties(quackShelter, q);
        q.lieu = LieuResponse.convert(quackShelter.getLieu());
        return q;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LieuResponse getLieu() {
        return lieu;
    }

    public void setLieu(LieuResponse lieu) {
        this.lieu = lieu;
    }

}
