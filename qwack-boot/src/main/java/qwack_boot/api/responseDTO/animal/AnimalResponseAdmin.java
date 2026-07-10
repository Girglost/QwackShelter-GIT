package qwack_boot.api.responseDTO.animal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import qwack_boot.dto.HistoriqueSanteDTO;
import qwack_boot.dto.VisiteDTO;
import qwack_boot.model.Animal;
import qwack_boot.model.Caractere;
import qwack_boot.model.Famille;
import qwack_boot.model.Genre;

public class AnimalResponseAdmin {

	private Integer id;
	private String nomAnimal;
	private LocalDate dateNaissance;
	private String couleur;
	private String regimeAlimentaire;
	private String traitement;
	private Famille famille;
	private Genre genre;
	private Integer idQuackShelter;
	private List<Caractere> caracteres;
	private List<VisiteDTO> visites;
	private List<HistoriqueSanteDTO> historiqueSantes;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static AnimalResponseAdmin convert(Animal animal) {
		AnimalResponseAdmin a = new AnimalResponseAdmin();
		BeanUtils.copyProperties(animal, a);
		a.idQuackShelter = animal.getQuackShelter().getId();
		return a;
	}

	public static AnimalResponseAdmin convertWithHistoriqueSante(Animal animal) {
		AnimalResponseAdmin a = convert(animal);

		a.setHistoriqueSantes(
				animal.getHistoriqueSante()
						.stream()
						.map(HistoriqueSanteDTO::convert)
						.toList());

		return a;
	}

	public static AnimalResponseAdmin convertWithVisites(Animal animal) {
		AnimalResponseAdmin a = convert(animal);

		a.setVisites(
				animal.getVisites()
						.stream()
						.map(VisiteDTO::convert)
						.toList());

		return a;
	}

	public List<VisiteDTO> getVisites() {
		return visites;
	}

	public void setVisites(List<VisiteDTO> visites) {
		this.visites = visites;
	}

	public List<HistoriqueSanteDTO> getHistoriqueSantes() {
		return historiqueSantes;
	}

	public void setHistoriqueSantes(List<HistoriqueSanteDTO> historiqueSantes) {
		this.historiqueSantes = historiqueSantes;
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

	public Integer getIdQuackShelter() {
		return idQuackShelter;
	}

	public void setIdQuackShelter(Integer idQuackShelter) {
		this.idQuackShelter = idQuackShelter;
	}

}
