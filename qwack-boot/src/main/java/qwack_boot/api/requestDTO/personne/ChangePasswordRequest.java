package qwack_boot.api.requestDTO.personne;

public class ChangePasswordRequest {

    String login;
    String ancienPassword;
    String nouveauPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAncienPassword() {
        return ancienPassword;
    }

    public void setAncienPassword(String ancienPassword) {
        this.ancienPassword = ancienPassword;
    }

    public String getNouveauPassword() {
        return nouveauPassword;
    }

    public void setNouveauPassword(String nouveauPassword) {
        this.nouveauPassword = nouveauPassword;
    }

}
