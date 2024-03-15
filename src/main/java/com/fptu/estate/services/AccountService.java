package com.fptu.estate.services;


import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.DTO.AccountRegisterRequest;

import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.CityEntity;
import com.fptu.estate.entities.CustomerEntity;
import com.fptu.estate.entities.InvestorEntity;

import com.fptu.estate.entities.*;

import com.fptu.estate.mapper.AccountMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.AgencyRepository;
import com.fptu.estate.repository.CityRepository;
import com.fptu.estate.repository.CustomerRepository;
import com.fptu.estate.repository.InvestorRepository;

import com.fptu.estate.services.imp.AccountServiceImp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements AccountServiceImp {

  @Autowired
  @Lazy
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private InvestorRepository investorRepository;

  @Autowired
  private AgencyRepository agencyRepository;

  @Autowired
  private AccountMapper accountMapper;

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private CityRepository cityRepository;


  @Override
  public boolean checkAvailableAccount(String email) {
    AccountEntity account = accountRepository.findByEmail(email);
    if(account != null) return false;
    return true;
  }

  @Override
  public boolean changeStatus(Integer id) {

    return accountRepository.findById(id).map(account -> {
      boolean status = account.isStatus();
      account.setStatus(!status);
      accountRepository.save(account);
      return true;
    }).orElse(false);
  }

  @Override
  public List<AccountDTO> getAll() {
    return accountRepository.findAll().stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public AccountDTO findById(Integer id) {
    AccountEntity account = accountRepository.findByIdAndStatus(id, true);
    if(account != null) {
      return modelMapper.map(account, AccountDTO.class);
    } else {
      return null;
    }
  }

  @Override
  public AccountDTO createAccount(AccountRegisterRequest accountRegisterRequest) {
    CityEntity city = new CityEntity();
    city.setId(accountRegisterRequest.getCityId());
    LocalDateTime now = LocalDateTime.now();
    String avatarURL ="https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/2048px-User-avatar.svg.png";

    // Convert LocalDateTime to Date
    Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    if(checkAvailableAccount(accountRegisterRequest.getEmail())){
      AccountEntity account = accountRepository.save(new AccountEntity
          (null, passwordEncoder.encode(accountRegisterRequest.getPassword()),
              accountRegisterRequest.getEmail(), avatarURL, accountRegisterRequest.getRole(),
              1,accountRegisterRequest.getDob(),date, date, 0.0, false, city, accountRegisterRequest.getName(), accountRegisterRequest.getPhoneNumber())) ;
      if(account.getRole().matches("CUSTOMER")){
        CustomerEntity customer = customerRepository.save(new CustomerEntity(null, account));
      }
      if (account.getRole().matches("AGENCY")) {
        AgencyEntity agency = agencyRepository.save(new AgencyEntity(null, account));
      }
      if (account.getRole().matches("INVESTOR")) {
        InvestorEntity investor = investorRepository.save(new InvestorEntity(null, account));
      }
      return modelMapper.map(account, AccountDTO.class);
    } else {
      System.out.println("Account is created yet!!!");
      return null;
    }
  }



  @Override
  public AccountDTO UpdateAccount(AccountDTO accountDTO, Integer id) {
    AccountEntity account = accountRepository.findByIdAndStatus(id, true);
    try {
      account.setCity(cityRepository.findById(accountDTO.getCityId()).orElseThrow(null));
      account.setAvatarUrl(accountDTO.getAvatarUrl());
      account.setGender(accountDTO.getGender());
      account.setDob(accountDTO.getDob());
      account.setName(accountDTO.getName());
      account.setPhoneNumber(accountDTO.getPhoneNumber());
      accountRepository.save(account);
      AccountDTO accountDTO1 = accountMapper.convertToDTO(account);
      return accountDTO1;
    } catch (Exception e) {
      throw new RuntimeException("Error create apartment" + e.getMessage());
    }

  }

  @Override
  public boolean updatePayment(Integer accId, Double amount) {
//    AccountDTO accountDTO = accountMapper.convertToDTO(accountRepository.findByIdAndStatus(accId, true));
//    Double balance = accountDTO.getBalance();
//    accountDTO.setBalance(balance + amount);
//    AccountEntity account = accountMapper.revertToEntity(accountDTO);
//    try{
//      accountRepository.save(account);
//    } catch (Exception e){
//      throw new RuntimeException("Error Update Payment" + e.getMessage());
//
//    }
     return accountRepository.findById(accId).map(account -> {
      Double balance = account.getBalance();
      Double newBalance = balance + amount;
      account.setBalance(newBalance);
      accountRepository.save(account);
      return true;
    }).orElse(false);


  }

}
