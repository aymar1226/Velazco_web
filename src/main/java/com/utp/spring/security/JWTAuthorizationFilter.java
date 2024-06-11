package com.utp.spring.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            if (usernamePAT != null) {
                // Obtener los roles del usuario desde el token
                List<GrantedAuthority> authorities = (List<GrantedAuthority>) usernamePAT.getAuthorities();

                // Crear un nuevo objeto UsernamePasswordAuthenticationToken con los roles
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(usernamePAT.getPrincipal(), usernamePAT.getCredentials(), authorities);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
