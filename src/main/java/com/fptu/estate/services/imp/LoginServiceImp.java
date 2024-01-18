package com.fptu.estate.services.imp;

import com.fptu.estate.entities.UserEntity;

public interface LoginServiceImp {
  UserEntity checkLogin(String email, String password);
}
