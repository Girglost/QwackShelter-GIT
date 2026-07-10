package qwack_boot.api.responseDTO.personne;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import qwack_boot.api.requestDTO.VisiteDTO;
import qwack_boot.api.responseDTO.StatutAnimalReponse;
import qwack_boot.api.responseDTO.lieu.LieuResponse;
import qwack_boot.model.Personne;
import qwack_boot.model.Role;

public class VisiteurResponse {
    private Integer id;
    private String nom;
    private String prenom;
    private String login;
    private Role role;
    private boolean admin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateInscription;
    private LieuResponse habitation;

    private Integer quackShelterId;

    private List<VisiteDTO> visites;
    private List<StatutAnimalReponse> adoptions;

    public static VisiteurResponse convert(Personne visiteur) {
        VisiteurResponse v = new VisiteurResponse();

        System.out.println(visiteur);

        v.id = visiteur.getId();
        v.nom = visiteur.getNom();
        v.prenom = visiteur.getPrenom();
        v.login = visiteur.getLogin();
        v.role = visiteur.getRole();
        v.admin = visiteur.isAdmin();
        v.dateInscription = visiteur.getDateInscription();

        // HABITATION
        v.habitation = LieuResponse.convert(visiteur.getHabitation());

        // QuackShelter
        v.quackShelterId = visiteur.getQuackShelter().getId();
        System.out.println(v);
        return v;
    }

    public static VisiteurResponse convertWithVisites(Personne visiteur) {
        VisiteurResponse v = VisiteurResponse.convert(visiteur);
        v.visites = visiteur.getVisites().stream().map(visite -> VisiteDTO.convert(visite)).toList();
        return v;
    }

    public static VisiteurResponse convertWithAdoptions(Personne visiteur) {
        VisiteurResponse v = VisiteurResponse.convert(visiteur);
        v.adoptions = visiteur.getAdoptions().stream().map(a -> StatutAnimalReponse.convert(a)).toList();
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

    public LieuResponse getHabitation() {
        return habitation;
    }

    public void setHabitation(LieuResponse habitation) {
        this.habitation = habitation;
    }

    public Integer getQuackShelterId() {
        return quackShelterId;
    }

    public void setQuackShelterId(Integer quackShelterId) {
        this.quackShelterId = quackShelterId;
    }

    public List<VisiteDTO> getVisites() {
        return visites;
    }

    public void setVisites(List<VisiteDTO> visites) {
        this.visites = visites;
    }

    public List<StatutAnimalReponse> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<StatutAnimalReponse> adoptions) {
        this.adoptions = adoptions;
    }

    @Override
    public String toString() {
        return "VisiteurResponse [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", role="
                + role + ", admin=" + admin + ", dateInscription=" + dateInscription + ", habitation=" + habitation
                + ", quackShelterId=" + quackShelterId + ", visites=" + visites + ", adoptions=" + adoptions + "]";
    }

}
