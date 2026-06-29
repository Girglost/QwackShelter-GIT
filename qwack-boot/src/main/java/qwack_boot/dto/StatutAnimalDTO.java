package qwack_boot.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.StatutAnimal;

/**
 * StatutAnimalDTO
 */
public class StatutAnimalDTO {
    private Integer id;
    private Integer idAnimal;// private AnimalDTO animal;
    private String nomAnimal;
    private LocalDateTime date;

    public static StatutAnimalDTO convert(StatutAnimal statutAnimal) {
        StatutAnimalDTO s = new StatutAnimalDTO();
        BeanUtils.copyProperties(statutAnimal, s);
        return s;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
