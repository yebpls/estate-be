package com.fptu.estate.services;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.DTO.CustomerDTO;
import com.fptu.estate.DTO.CustomerDetailDTO;
import com.fptu.estate.controller.CustomerController;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.mapper.AccountMapper;
import com.fptu.estate.mapper.CustomerMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.CustomerRepository;
import com.fptu.estate.services.imp.AccountServiceImp;
import com.fptu.estate.services.imp.CustomerServiceImp;
import java.util.List;
import java.util.stream.Collectors;
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

  @Autowired
  private AccountServiceImp accountServiceImp;

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

  @Override
  public List<CustomerDetailDTO> findAllCustomerDetail() {
    List<CustomerDTO> listCustomer = customerRepository.findAll().stream()
        .map(customerMapper::convertToDTO)
        .collect(Collectors.toList());

    List<CustomerDetailDTO> detailList = listCustomer.stream().map(customerDTO -> {
      // Fetch AccountDTO based on accountId
      AccountDTO accountDTO = accountServiceImp.findById(customerDTO.getAccountId());

      // Map both CustomerDTO and AccountDTO to CustomerDetailDTO
      CustomerDetailDTO detailDTO = new CustomerDetailDTO();
      detailDTO.setId(customerDTO.getId());
      detailDTO.setAccountId(customerDTO.getAccountId());

      // Assuming AccountDTO contains all fields needed by CustomerDetailDTO
      // Set fields from AccountDTO
      detailDTO.setPassword(accountDTO.getPassword());
      detailDTO.setEmail(accountDTO.getEmail());
      detailDTO.setAvatarUrl(accountDTO.getAvatarUrl());
      detailDTO.setRole(accountDTO.getRole());
      detailDTO.setGender(accountDTO.getGender());
      detailDTO.setBalance(accountDTO.getBalance());
      detailDTO.setDob(accountDTO.getDob());
      detailDTO.setStatus(accountDTO.isStatus());
      detailDTO.setCityId(accountDTO.getCityId());
      detailDTO.setName(accountDTO.getName());
      detailDTO.setPhoneNumber(accountDTO.getPhoneNumber());

      return detailDTO;
    }).collect(Collectors.toList());

    return detailList;
  }

}
