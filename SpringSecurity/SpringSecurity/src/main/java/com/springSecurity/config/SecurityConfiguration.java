/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author galax
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfiguration {

    @Bean
    //authentication method register as bean 
    // we can create class type bean or interface type bean 
    public UserDetailsService userDetailsService(PasswordEncoder encode) {

//        UserDetails user1 = User.withUsername("nitish").password(encode.encode("nitish")).roles("ADMIN").build();
//        UserDetails user2 = User.withUsername("manish").password(encode.encode("manish")).roles("USER").build();
//        return new InMemoryUserDetailsManager(user1, user2);

            //UserDetailsService um=new InMemoryUserDetailsManager(user1);
            //return new InMemoryUserDetailsManager(user1);
   
return new UserDetailFromDb();
    }

    @Bean
    //autherization method register as bean 
   // we can create class type bean or interface type bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disables CSRF protection
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/emp/addEmployee").permitAll()
                .requestMatchers("/emp/auth").permitAll()
//                .requestMatchers("/emp/getEmp").permitAll()
                .anyRequest().authenticated()
                ).formLogin(withDefaults());

        return http.build();

    }

    @Bean
       // we can create class type bean or interface type bean 

    public PasswordEncoder passwordEncoder() {
        PasswordEncoder pwd=new  BCryptPasswordEncoder();
        return pwd;
        
//        return new BCryptPasswordEncoder();

    }
}
