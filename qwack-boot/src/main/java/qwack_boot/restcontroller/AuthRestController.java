package qwack_boot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.dto.request.AuthRequest;
import qwack_boot.dto.request.AuthTokenResponse;
import qwack_boot.model.Personne;
import qwack_boot.security.JwtUtils;
import qwack_boot.service.PersonneService;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonneService personneSrv;

    @PostMapping
    public AuthTokenResponse auth(@RequestBody AuthRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        // System.out.println("DONNEES ENVOYEES");
        // System.out.println(request.login() + " - " + request.password());
        // On demande à Spring Security de vérifier l'authentification (username &
        // password OK ?)
        this.authenticationManager.authenticate(auth);
        System.out.println("AUTH " + auth.getPrincipal());
        // Si on arrive ici, c'est que l'authentification est OK
        // return JwtUtils.generate(request.login());
        return new AuthTokenResponse(JwtUtils.generate(request.login()));

    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        String login = (String) authentication.getPrincipal();
        Personne personne = personneSrv.getByLogin(login);

        System.out.println(personne);

        return "OK";

    }
}
