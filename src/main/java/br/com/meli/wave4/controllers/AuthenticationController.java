package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.TokenDTO;
import br.com.meli.wave4.config.TokenService;
import br.com.meli.wave4.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> getAuthentication(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken dataLogin = loginRequest.convert();
        Authentication authentication = manager.authenticate(dataLogin);
           String token = tokenService.getToken(authentication);
           return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    }
}

