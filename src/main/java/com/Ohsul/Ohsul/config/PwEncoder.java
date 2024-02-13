package com.Ohsul.Ohsul.config;

import org.springframework.security.crypto.password.*;

public class PwEncoder implements PasswordEncoder {
  @Override
  public String encode(CharSequence rawPassword){
    return rawPassword.toString();
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodePassword){
    return encodePassword.equals(encode(rawPassword));
  }
}
