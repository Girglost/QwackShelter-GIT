package quack.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Animal {
	
	protected int id;
	protected String nomAnimal;
	protected LocalDate dateNaissance;
	protected String couleur;
	protected String regimeAlimentaire;
	protected String traitement;
	protected Famille famille;
	protected Genre genre;
	protected Caractere caractere;
	protected QuackShelter qwackShelter;
	
	List<HistoriqueSante> historiqueSante = new ArrayList<>();
	List<StatutAnimal> statutAnimal = new ArrayList<>();
	
	public Animal(int id, String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, Caractere caractere, QuackShelter qwackShelter) {
		this.id = id;
		this.nomAnimal = nomAnimal;
		this.dateNaissance = dateNaissance;
		this.couleur = couleur;
		this.regimeAlimentaire = regimeAlimentaire;
		this.traitement = traitement;
		this.famille = famille;
		this.genre = genre;
		this.caractere = caractere;
		this.qwackShelter = qwackShelter;
	}

	public Animal() {
	}

	public int getId() {
		return id;
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

	public Famille getFamille() {
		return famille;
	}

	public Genre getGenre() {
		return genre;
	}

	public Caractere getCaractere() {
		return caractere;
	}

	public QuackShelter getQwackShelter() {
		return qwackShelter;
	}

	public List<HistoriqueSante> getHistoriqueSante() {
		return historiqueSante;
	}

	public List<StatutAnimal> getStatutAnimal() {
		return statutAnimal;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setCaractere(Caractere caractere) {
		this.caractere = caractere;
	}

	public void setQwackShelter(QuackShelter qwackShelter) {
		this.qwackShelter = qwackShelter;
	}

	public void setHistoriqueSante(List<HistoriqueSante> historiqueSante) {
		this.historiqueSante = historiqueSante;
	}

	public void setStatutAnimal(List<StatutAnimal> statutAnimal) {
		this.statutAnimal = statutAnimal;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nomAnimal=" + nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur="
				+ couleur + ", regimeAlimentaire=" + regimeAlimentaire + ", traitement=" + traitement + ", famille="
				+ famille + ", genre=" + genre + ", caractere=" + caractere + ", qwackShelter=" + qwackShelter
				+ ", historiqueSante=" + historiqueSante + "]";
	}
	
	
	
	
}
