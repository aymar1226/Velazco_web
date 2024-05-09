package com.utp.spring.security;

import com.utp.spring.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/productos/editar/**").hasRole("ADMIN")
                .antMatchers("/productos/nuevo").hasRole("ADMIN")
                .antMatchers("/listaproductos").hasRole("ADMIN")
                .antMatchers("/usuario/listausuarios").hasRole("ADMIN")
                .antMatchers("/productohome/**").authenticated()
                .antMatchers("/getCarrito").authenticated()
                .antMatchers("/pago").authenticated()
                .and().formLogin().loginPage("/usuario/login")
                .permitAll().defaultSuccessUrl("/usuario/acceder")
                .failureUrl("/usuario/login?error=true").and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/usuario/login?logout")
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
