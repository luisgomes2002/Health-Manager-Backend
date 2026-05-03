package com.health.manager.shared.config;

import com.health.manager.auth.model.AuthenticatedUser;
import com.health.manager.users.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsersRepository usersRepository;

    public SecurityFilter(TokenService tokenService, UsersRepository usersRepository) {
        this.tokenService = tokenService;
        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && !authorizationHeader.isBlank() && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());

            Optional<AuthenticatedUser> optionalUser = tokenService.verifyToken(token);
            if (optionalUser.isPresent()) {
                AuthenticatedUser userData = optionalUser.get();

                boolean isActive = usersRepository.findById(userData.userId())
                        .map(u -> u.isActive())
                        .orElse(false);

                if (isActive) {
                    var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + userData.role()));
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userData, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
