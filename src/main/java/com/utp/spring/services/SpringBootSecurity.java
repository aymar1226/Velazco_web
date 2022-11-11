package com.utp.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringBootSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/productos/editar/**").hasRole("ADMIN")
                .antMatchers("/productos/nuevo").hasRole("ADMIN")
                .antMatchers("/listaproductos").hasRole("ADMIN")
                .antMatchers("/usuario/listausuarios").hasRole("ADMIN")
                .antMatchers("/productohome/**").hasRole("ADMIN")
                .antMatchers("/productohome/**").hasRole("USER")
                .and().formLogin().loginPage("/usuario/login")
                .permitAll().defaultSuccessUrl("/usuario/acceder").permitAll();
    }

    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
