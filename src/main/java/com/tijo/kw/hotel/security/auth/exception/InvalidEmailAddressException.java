package com.tijo.kw.hotel.security.auth.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvalidEmailAddressException extends RuntimeException {
  public InvalidEmailAddressException(String errorMessage) {
    super(errorMessage);
  }
}

