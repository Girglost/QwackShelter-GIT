package qwack_boot.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Activer les annotations @PreAuthorize & @PostAuthorize
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderFilter jwtHeaderFilter) throws Exception {
        // Configuration des autorisations
        http.authorizeHttpRequests(auth -> {
            // On commence toujours par le plus spécifique, pour terminer par le plus
            // général
            auth.requestMatchers("/api/auth", "/api/inscription").permitAll();
            // Les utilisateurs doivent être authentifiés pour accéder à /quelquechose
            auth.requestMatchers("/**").authenticated();
        });

        // On désactive les Cookies
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Insérer le filtre AVANT un filtre UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtHeaderFilter, UsernamePasswordAuthenticationFilter.class);

        http.httpBasic(Customizer.withDefaults());

        http.cors(Customizer.withDefaults());

        // Désactivation de la protection CSRF uniquement pour les ressources /api/**
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("*"));

        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Yohann ENCODED = " + passwordEncoder.encode("Yohann"));
        System.out.println("Ronan ENCODED = " + passwordEncoder.encode("Ronan"));
        System.out.println("Clea ENCODED = " + passwordEncoder.encode("Clea"));
        System.out.println("Marie ENCODED = " + passwordEncoder.encode("Marie"));
        return passwordEncoder;
    }

    // Permet d'ajouter un AuthenticationManager de Spring Security dans le contexte
    // de Spring, pour pouvoir le récupérer ailleurs et l'utiliser
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
