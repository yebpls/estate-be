package com.fptu.estate.services;

import com.fptu.estate.entities.UserEntity;
import com.fptu.estate.repository.UserRepository;
import com.fptu.estate.services.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserEntity checkLogin(String email, String password) {
    UserEntity userEntity = userRepository.findByEmail(email);

    if(userEntity != null && passwordEncoder.matches(password, userEntity.getPassword())){
      return userEntity;
    }

    System.out.println(userEntity);
    return null;
  }
}
