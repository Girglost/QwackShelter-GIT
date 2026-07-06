package qwack_boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import qwack_boot.model.Personne;
import qwack_boot.service.PersonneService;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonneService personneSrv;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // System.out.println("USERNAME DANS DETAILSSERVICE : " + username);
        Personne personne = personneSrv.getByLogin(username);

        if (personne == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        // System.out.println("LA PERSONNE TROUVE a le mdp : " +
        // personne.getPassword());

        return User.builder()
                .username(username)
                .password(personne.getPassword())
                .build();

        // return new CustomUserDetails(personne);
    }
}
