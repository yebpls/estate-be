package com.fptu.estate.services;

import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.services.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public AccountEntity checkLogin(String email, String password) {
    AccountEntity account = accountRepository.findByEmail(email);

    if(account != null && passwordEncoder.matches(password, account.getPassword())){
      return account;
    }

    System.out.println(account);
    return null;
  }
}
