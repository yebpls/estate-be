package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.CustomerDTO;
import com.fptu.estate.DTO.CustomerDetailDTO;
import java.util.List;

public interface CustomerServiceImp {
  CustomerDTO findCustomerIdByAccountId(Integer accId);

  List<CustomerDetailDTO> findAllCustomerDetail();

}
