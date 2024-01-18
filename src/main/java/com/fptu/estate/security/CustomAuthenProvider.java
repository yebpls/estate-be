package com.fptu.estate.security;

import com.fptu.estate.entities.UserEntity;
import com.fptu.estate.services.imp.LoginServiceImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenProvider implements AuthenticationProvider {

  @Autowired
  private LoginServiceImp loginServiceImp;


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();
    UserEntity userEntity = loginServiceImp.checkLogin(email,password);
    if (userEntity != null) {
      List<GrantedAuthority> listRoles = new ArrayList<>();
      SimpleGrantedAuthority role = new SimpleGrantedAuthority(userEntity.getRole());
      listRoles.add(role);
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken("", "",listRoles);
      return authenticationToken;
    }
    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
