package qwack_boot.api.responseDTO.animal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;


import qwack_boot.model.Animal;
import qwack_boot.model.Caractere;
import qwack_boot.model.Famille;
import qwack_boot.model.Genre;

public class AnimalResponse {

	private Integer id;
	private String nomAnimal;
	private LocalDate dateNaissance;
	private String couleur;
	private String regimeAlimentaire;
	private String traitement;
	private Famille famille;
	private Genre genre;
	private List<Caractere> caracteres;
	private String typeAnimal;

	public static AnimalResponse convert(Animal animal) {
		AnimalResponse a = new AnimalResponse();
		BeanUtils.copyProperties(animal, a);
		a.typeAnimal = animal.getClass().getSimpleName();
		return a;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomAnimal() {
		return nomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getRegimeAlimentaire() {
		return regimeAlimentaire;
	}

	public void setRegimeAlimentaire(String regimeAlimentaire) {
		this.regimeAlimentaire = regimeAlimentaire;
	}

	public String getTraitement() {
		return traitement;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	public Famille getFamille() {
		return famille;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Caractere> getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(List<Caractere> caracteres) {
		this.caracteres = caracteres;
	}

	public String getTypeAnimal() {
		return typeAnimal;
	}

	public void setTypeAnimal(String typeAnimal) {
		this.typeAnimal = typeAnimal;
	}

	

}