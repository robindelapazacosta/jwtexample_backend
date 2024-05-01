package com.robin.jwtbackend.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserAuthProvider authProvider;


    /* SessionCreationPolicy.STATELESS
    * Es la autneticación sin estado, los datos de la sesión quedan almacenados del lado
    * del cliente. Se usa el token por lo que no es necesario usar usuario y contraseña en cada petición*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthFilter(authProvider), BasicAuthenticationFilter.class)  //Is the main authentication filter
                .sessionManagement(customizer->customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requets)->
                            requets.requestMatchers( HttpMethod.POST,"/login","/register").permitAll()
                                    .anyRequest().authenticated()
                        );

        return http.build();
    }

}
