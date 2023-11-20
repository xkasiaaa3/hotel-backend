package com.tijo.kw.hotel.security.auth;


import com.tijo.kw.hotel.security.config.JwtService;
import com.tijo.kw.hotel.user.Role;
import com.tijo.kw.hotel.user.User;
import com.tijo.kw.hotel.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

  UserRepository repository;
  PasswordEncoder passwordEncoder;
  JwtService jwtService;
  AuthenticationManager authenticationManager;

  public void register(RegisterRequest request) {
    var user = User.builder()
            .name(request.getFirstName())
            .surname(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
    repository.save(user);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
  }
}
