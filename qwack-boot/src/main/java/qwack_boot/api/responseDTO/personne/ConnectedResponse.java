package qwack_boot.api.responseDTO.personne;

import qwack_boot.api.responseDTO.lieu.LieuResponse;
import qwack_boot.model.Personne;
import qwack_boot.model.Role;

public class ConnectedResponse {

    private Integer id;

    private String nom;

    private String prenom;

    private String login;

    private Role role;

    private boolean admin;

    private LieuResponse habitation;

    private Integer quackShelterId;

    public static ConnectedResponse convert(Personne personne) {
        ConnectedResponse c = new ConnectedResponse();

        System.out.println(personne);

        c.id = personne.getId();
        c.nom = personne.getNom();
        c.prenom = personne.getPrenom();
        c.login = personne.getLogin();
        c.role = personne.getRole();
        c.admin = personne.isAdmin();

        // HABITATION
        c.habitation = LieuResponse.convert(personne.getHabitation());

        // QuackShelter
        c.quackShelterId = personne.getQuackShelter().getId();
        System.out.println(c);
        return c;
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

    @Override
    public String toString() {
        return "ConnectedResponse [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", role="
                + role + ", admin=" + admin + ", habitation=" + habitation + ", quackShelterId=" + quackShelterId + "]";
    }
}
