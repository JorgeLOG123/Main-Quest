package arg.jorge.mainquest.config;

import arg.jorge.mainquest.Enums.Rol;
import arg.jorge.mainquest.controllers.*;
import arg.jorge.mainquest.controllers.admins.AdminWebController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        return http
                .securityMatcher(BaseAPIController.BASE_URL + "/**")
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .build();
    }

    @Bean
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {

        return http
                .securityMatcher(BaseWebController.BASE_URL + "/**")
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HomeWebController.HOME_URL, GameWebController.GAME_AS_GUEST_URL).permitAll()
                        .requestMatchers(LoginWebController.LOGIN_URL, LoginWebController.SIGN_IN_URL).permitAll()
                        .requestMatchers(RegistracionWebController.REGISTRACION_URL).permitAll()
                        .requestMatchers(AdminWebController.ADMIN_URL + "/**").hasAnyRole(Rol.ADMINISTRADOR.name(), Rol.ARBITRO.name())
                        .anyRequest().authenticated())
                .formLogin(page -> page.loginPage(LoginWebController.SIGN_IN_URL))
                .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect(LoginWebController.SIGN_IN_URL);
        };
    }


}