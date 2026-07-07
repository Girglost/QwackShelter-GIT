package qwack_boot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import qwack_boot.model.TypeLieu;

public class LieuUpdateRequest {

    @NotNull
    private TypeLieu type;

    @NotBlank
    private String numero;

    @NotBlank
    private String voie;

    @NotBlank
    private String ville;

    @NotBlank
    private String cp;

    public LieuUpdateRequest() {
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

