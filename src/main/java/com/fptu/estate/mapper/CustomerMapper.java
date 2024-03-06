package com.fptu.estate.mapper;

import com.fptu.estate.DTO.CustomerDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
  @Autowired
  private ModelMapper modelMapper;

  public CustomerDTO convertToDTO(CustomerEntity customer){
    CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
    customerDTO.setAccountId(customer.getAccount().getId());
    return customerDTO;
  }

  public CustomerEntity revertToEntity(CustomerDTO customerDTO){
    CustomerEntity customer = modelMapper.map(customerDTO, CustomerEntity.class);
    AccountEntity account = new AccountEntity();
    account.setId(customerDTO.getAccountId());
    customer.setAccount(account);
    return customer;
  }


}
