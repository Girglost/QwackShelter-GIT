package qwack_boot.api.requestDTO.animal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import qwack_boot.model.Caractere;
import qwack_boot.model.Genre;
import qwack_boot.model.NAC;

public class UpdateNACRequest {
    
     private String nomAnimal;
    private LocalDate dateNaissance;
    private String couleur;
    private String regimeAlimentaire;
    private String traitement;
    private Genre genre;
    private boolean sterilisation;
    private boolean gestante;
    private String espece;
    private List<Caractere> caracteres;
    private Integer qwackShelterId;

    public static UpdateNACRequest convert(NAC nac) {
        UpdateNACRequest n = new UpdateNACRequest();
        BeanUtils.copyProperties(nac, n);
        n.qwackShelterId = nac.getQuackShelter().getId();
        return n;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setRegimeAlimentaire(String regimeAlimentaire) {
        this.regimeAlimentaire = regimeAlimentaire;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setSterilisation(boolean sterilisation) {
        this.sterilisation = sterilisation;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public void setCaracteres(List<Caractere> caracteres) {
        this.caracteres = caracteres;
    }

    public void setQwackShelterId(Integer qwackShelterId) {
        this.qwackShelterId = qwackShelterId;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public String getTraitement() {
        return traitement;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean isSterilisation() {
        return sterilisation;
    }

    public boolean isGestante() {
        return gestante;
    }

    public String getEspece() {
        return espece;
    }

    public List<Caractere> getCaracteres() {
        return caracteres;
    }

    public Integer getQwackShelterId() {
        return qwackShelterId;
    }
}
