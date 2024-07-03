package com.ndc.learning.studentrepository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
 * Date: 03/07/24
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

  @Bean
  public SecurityContextRepository securityContextRepository() {
    return new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
    );
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
    return http.
            csrf(csrf -> csrf.disable()).//1
            authorizeHttpRequests(auth-> {
              auth.anyRequest().authenticated(); // (2)
            }).
            sessionManagement(session-> {
              session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            }).//3
            httpBasic(Customizer.withDefaults()) //4
            .build();
  }


  // Password Encoding
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
