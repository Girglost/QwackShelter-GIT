package qwack_boot.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import qwack_boot.model.Personne;
import qwack_boot.model.Role;
import qwack_boot.service.PersonneService;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Autowired
    private PersonneService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            String token = authHeader.substring(7); // RETIRER BEARER
            String login = JwtUtils.validate(token);
            Personne personne = this.service.getByLogin(login);

            if (personne != null) {
                List<GrantedAuthority> authorities = new ArrayList<>();

                switch (personne.getRole()) {
                    case Role.PATRON:
                        authorities.add(new SimpleGrantedAuthority("ROLE_PATRON"));
                        break;
                    case Role.EMPLOYE:
                        authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYE"));
                        break;
                    case Role.BENEVOLE:
                        authorities.add(new SimpleGrantedAuthority("ROLE_BENEVOLE"));
                        break;
                    case Role.VISITEUR:
                        authorities.add(new SimpleGrantedAuthority("ROLE_VISITEUR"));
                        break;

                    default:
                        break;
                }

                if (personne.isAdmin()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }

                Authentication auth = new UsernamePasswordAuthenticationToken(new CustomUserDetails(personne), null,
                        authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // On pense à passer à la suite
        filterChain.doFilter(request, response);
    }
}
