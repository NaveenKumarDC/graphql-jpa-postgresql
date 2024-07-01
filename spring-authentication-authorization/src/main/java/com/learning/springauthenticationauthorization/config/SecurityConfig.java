package com.learning.springauthenticationauthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;


/**
 * Author: Naveen Kumar D C
 * Date: 01/07/24
 */
@Configuration // required after 6.1
@EnableWebSecurity
//In 6.0, @Configuration is removed from @EnableWebSecurity, @EnableMethodSecurity, @EnableGlobalMethodSecurity, and @EnableGlobalAuthentication.
@EnableMethodSecurity
// In 6.0, @Configuration is removed from @EnableWebSecurity, @EnableMethodSecurity, @EnableGlobalMethodSecurity, and @EnableGlobalAuthentication.
public class SecurityConfig {

  // User Creation
  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {

    // InMemory Users.
    //User with the ADMIN and USER Roles
    UserDetails admin = User.withUsername("Naveen")
            .password(encoder.encode("Naveen"))
            .roles("ADMIN", "USER")
            .build();

    //User with the ADMIN and USER Roles
    UserDetails user = User.withUsername("User")
            .password(encoder.encode("User"))
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(admin, user);
  }

  // Configuring HttpSecurity after Spring security 6.1...
  @Bean
  public SecurityContextRepository securityContextRepository() {
    return new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
    );
  }

  // Configuring HttpSecurity after Spring security 6.1...
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
    http.
            //To add custom matchers for authentication
//            securityMatchers((matchers) -> matchers
//            .requestMatchers("/api/**", "/app/**", "/admin/**")
//            .requestMatchers(new MyCustomRequestMatcher())).
        authorizeHttpRequests((authz) -> authz
        .requestMatchers("/api/admin/**").hasRole("ADMIN")
        .requestMatchers("/api/user/**").hasRole("USER")
        .anyRequest().authenticated()).formLogin(AbstractAuthenticationFilterConfigurer::permitAll

    );

    return http.build();
  }


  // Password Encoding
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
