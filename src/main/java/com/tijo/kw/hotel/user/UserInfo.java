package com.tijo.kw.hotel.user;

import com.tijo.kw.hotel.user.dto.UserDetailsDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserInfo extends UserDetails {
  UserDetailsDto getUserDetails();
}
