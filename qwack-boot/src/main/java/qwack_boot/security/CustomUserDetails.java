package qwack_boot.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import qwack_boot.model.Personne;

public class CustomUserDetails implements UserDetails {
    private final Personne personne;

    public CustomUserDetails(Personne personne) {
        this.personne = personne;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return personne.getPassword();
    }

    @Override
    public String getUsername() {
        return personne.getLogin();
    }

    public Personne getPersonne() {
        return personne;
    }

}
