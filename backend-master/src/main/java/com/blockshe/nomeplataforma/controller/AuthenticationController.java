package com.blockshe.nomeplataforma.controller;

import com.blockshe.nomeplataforma.model.LoginResponse;
import com.blockshe.nomeplataforma.model.User;
import com.blockshe.nomeplataforma.model.UserLogin;
import com.blockshe.nomeplataforma.service.AuthenticationService;
import com.blockshe.nomeplataforma.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<User> cadastrar(@RequestBody User user) {
        User registeredUser = authenticationService.cadastrarUser(user);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/entrar")
    public ResponseEntity<LoginResponse> entrar(@RequestBody UserLogin userLogin){
        User authenticatedUser = authenticationService.authenticate(userLogin);

        String jwtToken = jwtService.generateToken((UserDetails) authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
