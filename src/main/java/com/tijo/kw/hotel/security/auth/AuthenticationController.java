package com.tijo.kw.hotel.security.auth;

import com.tijo.kw.hotel.security.auth.exception.InvalidEmailAddressException;
import com.tijo.kw.hotel.security.auth.exception.UserAlreadyExists;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
  private final AuthenticationService service;

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/register")
  @Operation(summary = "Register new user")
  public ResponseEntity<Boolean> register(@RequestBody RegisterRequest request) {
    try {
      boolean response = service.register((request));
      return ResponseEntity.ok(response);
    } catch (UserAlreadyExists e) {
      return ResponseEntity.status(409).build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/authenticate")
  @Operation(summary = "Sign in")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    try {
      AuthenticationResponse response = service.authenticate(request);
      return ResponseEntity.ok(response);
    } catch (InvalidEmailAddressException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthenticationResponse("Incorrect email address."));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse("Serwer error."));
    }

  }

  @Hidden
  @PostMapping("/cleanup")
  public ResponseEntity<Boolean> cleanup() {
    service.cleanup();
    return ResponseEntity.ok(true);
  }
}
