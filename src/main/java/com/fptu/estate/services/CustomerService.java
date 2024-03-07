package com.fptu.estate.services;

import com.fptu.estate.DTO.CustomerDTO;
import com.fptu.estate.controller.CustomerController;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.mapper.AccountMapper;
import com.fptu.estate.mapper.CustomerMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.CustomerRepository;
import com.fptu.estate.services.imp.CustomerServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceImp {
  private Logger log = LoggerFactory.getLogger(CustomerController.class);


  private final AccountRepository accountRepository;

  @Autowired
  private CustomerMapper customerMapper;

  @Autowired
  private CustomerRepository customerRepository;

  public CustomerService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public CustomerDTO findCustomerIdByAccountId(Integer accId) {
    log.info("Searching for account with ID: {}", accId);

    AccountEntity account = accountRepository.findByIdAndStatus(accId, true);
    if (account == null) {
      log.warn("Account not found for ID: {}", accId);
      return null; // Or handle this scenario appropriately
    }
    log.info("Account found, searching for customer linked to account ID: {}", accId);
    CustomerDTO customerDTO = customerMapper.convertToDTO(customerRepository.findByAccount(account));
    return customerDTO;
  }
}
