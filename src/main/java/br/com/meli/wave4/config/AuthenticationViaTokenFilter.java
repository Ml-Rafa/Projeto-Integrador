package br.com.meli.wave4.config;

import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationViaTokenFilter extends OncePerRequestFilter {


    private TokenService tokenService;
    private UserRepository repository;

    public AuthenticationViaTokenFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //obtem token do cabecalho da requisicao
        String token = extractToken(request);
        //validar token
        boolean validToken = tokenService.validToken(token);

        if (validToken) {
            //autenticar o token

            performsTokenAuthenticationInSpring(token);
        }
        filterChain.doFilter(request, response);
    }

    private void performsTokenAuthenticationInSpring(String token) {
        String userName = tokenService.getUsername(token);
        User user = this.repository.findByUser(userName);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication); //forçando autenticacao pelo spring
    }

    private String extractToken(HttpServletRequest request) {
        String token = "";
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty() || !authorization.startsWith("Bearer ")) {
            return null;
        } else {
            token = authorization.substring(7, authorization.length());
        }
        return token;
    }
}
