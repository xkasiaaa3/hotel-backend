package com.tijo.kw.hotel.security.auth;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ValidateEmail {
  static boolean validateEmail(String email) {
    String patternString = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }
}
