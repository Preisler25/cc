package preisler.com.crazy_counter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // CSRF can be enabled if needed for forms
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login", "/user/register").permitAll()  // Allow public access to login/register
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(login -> login  // Enable form-based authentication
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/home", true)  // Default success URL after login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession()  // Protect against session fixation attacks
                        .maximumSessions(1)  // Limit to 1 session per user
                        .expiredUrl("/login?expired")  // Redirect to login on session expiration
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt password encoder
    }
}
