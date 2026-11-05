package arg.jorge.mainquest.config;

import arg.jorge.mainquest.Enums.Rol;
import arg.jorge.mainquest.controllers.GameController;
import arg.jorge.mainquest.controllers.HomeController;
import arg.jorge.mainquest.controllers.LoginController;
import arg.jorge.mainquest.controllers.RegistracionController;
import arg.jorge.mainquest.controllers.admins.AdminController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HomeController.HOME_URL, GameController.GAME_AS_GUEST_URL).permitAll()
                        .requestMatchers(LoginController.LOGIN_URL, LoginController.SIGN_IN_URL).permitAll()
                        .requestMatchers(RegistracionController.REGISTRACION_URL).permitAll()
                        .requestMatchers(AdminController.ADMIN_URL + "/**").hasAnyRole(Rol.ADMINISTRADOR.name(), Rol.ARBITRO.name())
                        .anyRequest().authenticated())
                .formLogin(page -> page.loginPage(LoginController.SIGN_IN_URL))
                .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect(LoginController.SIGN_IN_URL);
        };
    }


}