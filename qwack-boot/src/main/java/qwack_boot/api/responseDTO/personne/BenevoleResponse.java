package qwack_boot.api.responseDTO.personne;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import qwack_boot.dto.StatutAnimalDTO;
import qwack_boot.model.Personne;
import qwack_boot.model.Role;
import qwack_boot.model.StatutActivite;

public class BenevoleResponse {
    private Integer id;
    private String nom;
    private String prenom;
    private String login;
    private Role role;
    private boolean admin;
    private StatutActivite statutActivite;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEngagement;
    // private CreateLieuRequest habitation;

    private Integer quackShelterId;

    private List<StatutAnimalDTO> adoptions;

    public static BenevoleResponse convert(Personne benevole) {
        BenevoleResponse b = new BenevoleResponse();

        System.out.println(benevole);

        b.id = benevole.getId();
        b.nom = benevole.getNom();
        b.prenom = benevole.getPrenom();
        b.login = benevole.getLogin();
        b.role = benevole.getRole();
        b.admin = benevole.isAdmin();
        b.dateEngagement = benevole.getDateEngagement();
        b.setStatutActivite(benevole.getStatutActivite());

        // HABITATION
        // b.habitation = CreateLieuRequest.convert(benevole.getHabitation());

        // QuackShelter
        b.quackShelterId = benevole.getQuackShelter().getId();
        System.out.println(b);
        return b;
    }

    public static BenevoleResponse convertWithAdoptions(Personne benevole) {
        BenevoleResponse b = BenevoleResponse.convert(benevole);
        b.adoptions = benevole.getAdoptions().stream().map(a -> StatutAnimalDTO.convert(a)).toList();
        return b;
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

    public StatutActivite getStatutActivite() {
        return statutActivite;
    }

    public void setStatutActivite(StatutActivite statutActivite) {
        this.statutActivite = statutActivite;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public LocalDate getDateEngagement() {
        return dateEngagement;
    }

    public void setDateEngagement(LocalDate dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    public Integer getQuackShelterId() {
        return quackShelterId;
    }

    public void setQuackShelterId(Integer quackShelterId) {
        this.quackShelterId = quackShelterId;
    }

    public List<StatutAnimalDTO> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<StatutAnimalDTO> adoptions) {
        this.adoptions = adoptions;
    }

    @Override
    public String toString() {
        return "BenevoleResponse [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", role="
                + role + ", admin=" + admin + ", statutActivite=" + statutActivite + ", dateEngagement="
                + dateEngagement + ", quackShelterId=" + quackShelterId + ", adoptions=" + adoptions + "]";
    }

}
