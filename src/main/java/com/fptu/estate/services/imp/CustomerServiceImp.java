package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.CustomerDTO;

public interface CustomerServiceImp {
  CustomerDTO findCustomerIdByAccountId(Integer accId);
}
