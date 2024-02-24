package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.DTO.AccountRegisterRequest;
import com.fptu.estate.entities.AccountEntity;
import java.util.List;

public interface AccountServiceImp {
  boolean checkAvailableAccount(String email);

  boolean changeStatus(Integer id);

  List<AccountDTO> getAll();

  AccountDTO createAccount(AccountRegisterRequest accountRegisterRequest);

  AccountDTO findById(Integer id);
}
