package com.fptu.estate.services.imp;

import com.fptu.estate.entities.AccountEntity;

public interface LoginServiceImp {
  AccountEntity checkLogin(String email, String password);
}
