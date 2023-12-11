package com.tijo.kw.hotel.security.auth;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
  private final AuthenticationService service;
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value="/register")
  public void register(@RequestBody RegisterRequest request){
    service.register((request));
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value="/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
    return ResponseEntity.ok(service.authenticate(request));

  }
}
