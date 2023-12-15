package com.tijo.kw.hotel.user;

import com.tijo.kw.hotel.user.dto.UserDetailsDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
@Entity
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;
  String name;
  String surname;
  String email;
  String password;
  @Enumerated(EnumType.STRING)
  Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public UserDetailsDto getUserDetails() {
    return UserDetailsDto.builder()
            .id(id)
            .email(email)
            .firstName(name)
            .lastName(surname)
            .role(role)
            .build();
  }
}
