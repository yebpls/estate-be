package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.DTO.AccountRegisterRequest;
import com.fptu.estate.entities.AccountEntity;

public interface AccountServiceImp {
  boolean checkAvailableAccount(String email);

  AccountDTO createAccount(AccountRegisterRequest accountRegisterRequest);

  AccountDTO findById(Integer id);

  void UpdateAccount(AccountDTO accountDTO) throws Exception;

}
