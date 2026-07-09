package qwack_boot.api.responseDTO.personne;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import qwack_boot.model.Personne;
import qwack_boot.model.Role;
import qwack_boot.model.StatutActivite;

public class EmployeResponse {
    private Integer id;
    private String nom;
    private String prenom;
    private String login;
    private Role role;
    private boolean admin;
    private StatutActivite statutActivite;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEmbauche;
    private Integer quackShelterId;

    public static EmployeResponse convert(Personne employe) {
        EmployeResponse e = new EmployeResponse();

        System.out.println(employe);

        e.id = employe.getId();
        e.nom = employe.getNom();
        e.prenom = employe.getPrenom();
        e.login = employe.getLogin();
        e.role = employe.getRole();
        e.admin = employe.isAdmin();
        e.dateEmbauche = employe.getDateEmbauche();
        e.setStatutActivite(employe.getStatutActivite());

        // HABITATION
        // b.habitation = CreateLieuRequest.convert(benevole.getHabitation());

        // QuackShelter
        e.quackShelterId = employe.getQuackShelter().getId();
        System.out.println(e);
        return e;
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

    public StatutActivite getStatutActivite() {
        return statutActivite;
    }

    public void setStatutActivite(StatutActivite statutActivite) {
        this.statutActivite = statutActivite;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Integer getQuackShelterId() {
        return quackShelterId;
    }

    public void setQuackShelterId(Integer quackShelterId) {
        this.quackShelterId = quackShelterId;
    }

}
