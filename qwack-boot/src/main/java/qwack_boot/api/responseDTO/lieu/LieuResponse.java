package qwack_boot.api.responseDTO.lieu;
import qwack_boot.model.Lieu;
import qwack_boot.model.TypeLieu;

public class LieuResponse {

    private Integer id;
    private TypeLieu type;
    private String numero;
    private String voie;
    private String ville;
    private String cp;

    public LieuResponse() {
    }

       public static LieuResponse convert(Lieu lieu) {
       
        if (lieu == null) {
        return null;
    }
        LieuResponse response = new LieuResponse();

        response.setId(lieu.getId());
        response.setType(lieu.getType());
        response.setNumero(lieu.getAdresse().getNumero());
        response.setVoie(lieu.getAdresse().getVoie());
        response.setVille(lieu.getAdresse().getVille());
        response.setCp(lieu.getAdresse().getCp());

        return response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeLieu getType() {
        return type;
    }

    public void setType(TypeLieu type) {
        this.type = type;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}