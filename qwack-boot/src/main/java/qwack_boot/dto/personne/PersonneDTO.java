package qwack_boot.dto.personne;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import qwack_boot.dto.LieuDTO;
import qwack_boot.dto.QuackShelterDTO;
import qwack_boot.dto.StatutAnimalDTO;
import qwack_boot.dto.VisiteDTO;
import qwack_boot.model.Personne;
import qwack_boot.model.Role;

public class PersonneDTO {

    private Integer id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private Role role;
    private boolean admin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateInscription;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEngagement;
    private double salaire;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEmbauche;
    // HABITATION
    private LieuDTO habitation;

    // QuackShelter
    private QuackShelterDTO quackShelter;

    // Visites

    private List<VisiteDTO> visites;
    private List<StatutAnimalDTO> adoptions;

    public static PersonneDTO convert(Personne personne) {
        PersonneDTO p = new PersonneDTO();
        BeanUtils.copyProperties(personne, p);

        // HABITATION
        p.habitation = LieuDTO.convert(personne.getHabitation());

        // QuackShelter
        p.quackShelter = QuackShelterDTO.convert(personne.getQuackShelter());

        return p;
    }

    public static PersonneDTO convertWithVisites(Personne personne) {
        PersonneDTO visiteur = PersonneDTO.convert(personne);
        visiteur.visites = personne.getVisites().stream().map(v -> VisiteDTO.convert(v)).toList();
        return visiteur;
    }

    public static PersonneDTO convertWithAdoptions(Personne personne) {
        PersonneDTO visiteur = PersonneDTO.convert(personne);
        System.out.println(personne.getAdoptions());
        visiteur.adoptions = personne.getAdoptions().stream().map(a -> StatutAnimalDTO.convert(a)).toList();
        return visiteur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public LocalDate getDateEngagement() {
        return dateEngagement;
    }

    public void setDateEngagement(LocalDate dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public LieuDTO getHabitation() {
        return habitation;
    }

    public void setHabitation(LieuDTO habitation) {
        this.habitation = habitation;
    }

    public QuackShelterDTO getQuackShelter() {
        return quackShelter;
    }

    public void setQuackShelter(QuackShelterDTO quackShelter) {
        this.quackShelter = quackShelter;
    }

    public List<VisiteDTO> getVisites() {
        return visites;
    }

    public void setVisites(List<VisiteDTO> visites) {
        this.visites = visites;
    }

    public List<StatutAnimalDTO> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<StatutAnimalDTO> adoptions) {
        this.adoptions = adoptions;
    }

}
