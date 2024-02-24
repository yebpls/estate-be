package com.fptu.estate.services;

import com.fptu.estate.DTO.InvestorDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.mapper.InvestorMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.InvestorRepository;
import com.fptu.estate.services.imp.InvestorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestorService implements InvestorServiceImp {
  @Autowired
  private InvestorRepository investorRepository;
  @Autowired
  private InvestorMapper investorMapper;
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public InvestorDTO findInvestorById(Integer id) {
    AccountEntity account = accountRepository.findByIdAndStatus(id, true);
    InvestorDTO investorDTO = investorMapper.convertToDTO(investorRepository.findByAccount(account));
    if(investorDTO != null){
      return investorDTO;
    }
    return null;
  }
}
