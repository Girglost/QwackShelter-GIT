package qwack_boot.api.responseDTO.personne;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import qwack_boot.api.requestDTO.VisiteDTO;
import qwack_boot.api.responseDTO.StatutAnimalReponse;
import qwack_boot.api.responseDTO.lieu.LieuResponse;
import qwack_boot.model.Personne;
import qwack_boot.model.Role;
import qwack_boot.model.StatutActivite;

public class PersonneResponse {

    private Integer id;

    private String nom;

    private String prenom;

    private String login;

    private LieuResponse habitation;

    private Role role;

    private boolean admin;

    private StatutActivite statutActivite;

    private Integer quackShelterId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateInscription;

    // Concerne un benevole
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEngagement;

    // Concerne un employé ( un patron?)

    private double salaire;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEmbauche;

    private List<StatutAnimalReponse> adoptions;

    private List<VisiteDTO> visites;

    public static PersonneResponse convert(Personne personne) {
        PersonneResponse v = new PersonneResponse();

        System.out.println(personne);

        v.id = personne.getId();
        v.nom = personne.getNom();
        v.prenom = personne.getPrenom();
        v.login = personne.getLogin();
        v.role = personne.getRole();
        v.admin = personne.isAdmin();
        v.dateInscription = personne.getDateInscription();
        v.dateEngagement = personne.getDateEngagement();
        v.dateEmbauche = personne.getDateEmbauche();
        v.salaire = personne.getSalaire();
        v.statutActivite = personne.getStatutActivite();

        // HABITATION
        v.habitation = LieuResponse.convert(personne.getHabitation());

        // QuackShelter
        v.quackShelterId = personne.getQuackShelter().getId();
        System.out.println(v);
        return v;
    }

    public static PersonneResponse convertWithVisitesAndAdoptions(Personne personne) {
        PersonneResponse v = PersonneResponse.convert(personne);
        v.visites = personne.getVisites().stream().map(visite -> VisiteDTO.convert(visite)).toList();
        v.adoptions = personne.getAdoptions().stream().map(a -> StatutAnimalReponse.convert(a)).toList();
        return v;
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

    public LieuResponse getHabitation() {
        return habitation;
    }

    public void setHabitation(LieuResponse habitation) {
        this.habitation = habitation;
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

    public StatutActivite getStatutActivite() {
        return statutActivite;
    }

    public void setStatutActivite(StatutActivite statutActivite) {
        this.statutActivite = statutActivite;
    }

    public Integer getQuackShelterId() {
        return quackShelterId;
    }

    public void setQuackShelterId(Integer quackShelterId) {
        this.quackShelterId = quackShelterId;
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

    public List<StatutAnimalReponse> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<StatutAnimalReponse> adoptions) {
        this.adoptions = adoptions;
    }

    public List<VisiteDTO> getVisites() {
        return visites;
    }

    public void setVisites(List<VisiteDTO> visites) {
        this.visites = visites;
    }

}
