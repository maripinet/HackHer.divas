package com.blockshe.nomeplataforma.service;


import com.blockshe.nomeplataforma.model.User;
import com.blockshe.nomeplataforma.model.UserLogin;
import com.blockshe.nomeplataforma.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User cadastrarUser(User input) {

        User user = new User();
        user.setNome(input.getNome());
        user.setEmail(input.getEmail());
        user.setSenha(passwordEncoder.encode(input.getSenha()));

        return (userRepository.save(user));
    }

    public User authenticate(UserLogin input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getSenha()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
